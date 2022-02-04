package learning.library.i18n;

import java.awt.*;
import java.util.ListResourceBundle;

public class ProgramResources_de extends ListResourceBundle {
    private static final Object[][] contents =
            {       {"backgroundColor", Color.black},
                    {"defaultPaperSize", new double[]{210, 297}}
            };

    @Override
    public Object[][] getContents() {
        return contents;
    }
}
