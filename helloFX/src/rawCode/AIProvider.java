package rawCode;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class AIProvider {

    private static String apiKey = "";

    // ===================== QUIZ =====================
    public ArrayList<Question> generateQuiz(String info, int TF, int N, int MC) {

        ArrayList<Question> set = new ArrayList<>();

        try {

            String AiText = sendToAI(info, TF, N, MC, 0, "Quiz");
            String[] lines = AiText.split("\\|");

            int idx = 0;

            // TF
            for (int i = 0; i < TF; i++) {

                String question = lines[idx++];
                String answer = lines[idx++];

                set.add(new Question(question, answer, "TF"));
            }

            // N
            for (int i = 0; i < N; i++) {

                String question = lines[idx++];
                String answer = lines[idx++];

                set.add(new Question(question, answer, "N"));
            }

            // MC
            for (int i = 0; i < MC; i++) {

                String question = lines[idx++];
                String option1 = lines[idx++];
                String option2 = lines[idx++];
                String option3 = lines[idx++];
                String option4 = lines[idx++];
                String answer = lines[idx++];

                set.add(new Question(
                        question,
                        answer,
                        option1,
                        option2,
                        option3,
                        option4,
                        "MC"
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

    // ===================== FLASHCARDS =====================
    public ArrayList<Flashcard> generateFlashcards(String info, int FC) {

        ArrayList<Flashcard> set = new ArrayList<>();

        try {

            String AiText = sendToAI(info, 0, 0, 0, FC, "FlashCard");
            String[] lines = AiText.split("\\|");

            int idx = 0;

            for (int i = 0; i < FC; i++) {

                String front = lines[idx++];
                String back = lines[idx++];

                set.add(new Flashcard(front, back));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

    // ===================== AI CALL =====================
    public static String sendToAI(
            String info,
            int TF,
            int N,
            int MC,
            int FC,
            String type
    ) throws IOException {

        URL url = new URL(
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
            + apiKey
        );

        HttpURLConnection conn =
            (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String prompt = buildPrompt(info, TF, N, MC, FC, type);

        String escapedPrompt = prompt
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n");

        String json =
            "{ \"contents\": [{ \"parts\": [{ \"text\": \"" +
            escapedPrompt +
            "\" }] }] }";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        BufferedReader br =
            new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        String text = "";

        while ((line = br.readLine()) != null) {
            text += line;
        }

        br.close();

        return text;
    }

    // ===================== PROMPT BUILDER =====================
    private static String buildPrompt(
            String information,
            int TFs,
            int Ns,
            int MCs,
            int FCs,
            String type
    ) {

        if ("Quiz".equals(type)) {

            return
                "You are a strict question/quiz generator.\n" +
                "- Use | only\n" +
                "- No extra text\n" +
                "- Order: TF, N, MC\n" +
                "CONTENT:\n" +
                information;

        } else {

            return
                "You are a strict FlashCard generator.\n" +
                "- Use | only\n" +
                "- Format: |front|back|\n" +
                "- Number of flashcards: " + FCs + "\n" +
                "CONTENT:\n" +
                information;
        }
    }
}