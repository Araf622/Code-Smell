package workshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PlaintextToHtmlConverter {

        String source;
        List<String> result;
        List<String> convertedLine;
        String characterToConvert;
        Map<String, String> relation = new HashMap<String, String>();

        public String toHtml() throws Exception {
            String text = read();
            String htmlLines = basicHtmlEncode(text);
            return htmlLines;
        }

        protected String read() throws IOException {
            return new String(Files.readAllBytes(Paths.get("sample.txt")));
        }

        private String basicHtmlEncode(String source) {
            this.source = source;
            int i = 0;
            result = new ArrayList<>();
            convertedLine = new ArrayList<>();
            characterToConvert = stashNextCharacterAndAdvanceThePointer(i);
            loadCorrespondingValues();

            while (i <= this.source.length()) {
                addNewCharacter(characterToConvert);

                if (i >= source.length()) break;

                characterToConvert = stashNextCharacterAndAdvanceThePointer(i);
            }
            addANewLine();
            String finalResult = String.join("<br />", result);
            return finalResult;
        }

        //pick the character from source string
        //and increment the pointer
        private String stashNextCharacterAndAdvanceThePointer(int i) {
            char c = source.charAt(i);
            i += 1;
            return String.valueOf(c);
        }

        //stringfy convertedLine array and push into result
        //reset convertedLine
        private void addANewLine() {
            String line = String.join("", convertedLine);
            result.add(line);
            convertedLine = new ArrayList<>();
        }

        private void pushACharacterToTheOutput() {
            convertedLine.add(characterToConvert);
        }

        private void addNewCharacter(String c){
            for (Map.Entry<String, String> me : relation.entrySet()) {
                if(c.equals("\n"))    addANewLine();
                else if(me.getKey().equals(c))  convertedLine.add(me.getValue());
                else pushACharacterToTheOutput();
            }
        }
        public void loadCorrespondingValues(){
            relation.put("<", "&lt;");
            relation.put(">", "&gt;");
            relation.put("&", "&amp;");
            relation.put("&", "&");
        }
}
