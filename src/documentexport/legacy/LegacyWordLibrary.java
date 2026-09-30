package documentexport.legacy;

public class LegacyWordLibrary {

    public int createDocument(char[] data, int mode, String path) {

        if (data == null || data.length == 0) {
            return 10;
        }

        if (path == null || path.isBlank()) {
            return 20;
        }

        if (mode < 0) {
            return 30;
        }

        System.out.println();
        System.out.println("LEGACY WORD LIBRARY");
        System.out.println("Path: " + path);
        System.out.println("Mode: " + mode);
        System.out.println("Content: " + new String(data));

        return 0;
    }
}