package learning.io.serial;

import java.io.*;

public class SerialCloneable implements Cloneable, Serializable {

    public Object clone() throws CloneNotSupportedException {
        try {
            var bout = new ByteArrayOutputStream();
            try (var out = new ObjectOutputStream(bout)) {
                out.writeObject(this);
            }
            try (var bin = new ByteArrayInputStream(bout.toByteArray())) {
                var in = new ObjectInputStream(bin);
                return in.readObject();
            }

        } catch (ClassNotFoundException | IOException e) {
            var e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}
