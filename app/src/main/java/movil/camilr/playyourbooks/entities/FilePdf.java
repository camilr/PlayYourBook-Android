package movil.camilr.playyourbooks.entities;

import com.orm.SugarRecord;

/**
 * Created by Cami on 6/10/2015.
 */
public class FilePdf extends SugarRecord{
    String name;
    String direccion;
    String DirName;

    public FilePdf() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }
}
