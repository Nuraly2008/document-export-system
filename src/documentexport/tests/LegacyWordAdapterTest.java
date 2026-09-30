package documentexport.tests;

import documentexport.adapter.LegacyWordAdapter;
import documentexport.exception.ExportException;
import documentexport.legacy.LegacyWordLibrary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LegacyWordAdapterTest {

    static class BrokenLegacyLibrary extends LegacyWordLibrary {

        @Override
        public int createDocument(
                char[] data,
                int mode,
                String path
        ) {
            return 20;
        }
    }

    @Test
    void adapterTranslatesLegacyError() {

        LegacyWordLibrary legacy =
                new BrokenLegacyLibrary();

        LegacyWordAdapter adapter =
                new LegacyWordAdapter(legacy);

        assertThrows(
                ExportException.class,
                () -> adapter.export(
                        "Hello",
                        "test"
                )
        );
    }
}
