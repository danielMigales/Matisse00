package matisse00;

import biblioteca.Autor;
import biblioteca.Libro;
import biblioteca.Obra;
import com.matisse.MtDatabase;
import com.matisse.MtException;
import com.matisse.MtPackageObjectFactory;

public class Matisse00 {

    public static void main(String[] args) {
        
        
    }

    public static void creaObjetos(String hostname, String dbname) {

        try {
            //ABRE LA BASE DE DATOS CON EL HOSTNAME (LOCALHOST), DBNAME (RAMA) Y EL NAMESPACE "BIBLIOTECA"
            MtDatabase db = new MtDatabase(hostname, dbname, new MtPackageObjectFactory("biblioteca", ""));
            
            //ABRE LA BASE DE DATOS Y EMPIEZA LA TRANSACCION
            db.open();
            db.startTransaction();
            
            //CREA UN OBJETO AUTOR
            Autor a1 = new Autor(db);
            a1.setNombre("Haruki");
            a1.setApellidos("Murakami");
            a1.setEdad(53);

            //CREA UN OBJETO LIBRO
            Libro l1 = new Libro(db);
            l1.setTitulo("Baila Baila Baila");
            l1.setEditorial("Tusquets");
            l1.setPaginas(512);

            Libro l2 = new Libro(db);
            l1.setTitulo("Tokio Blues");
            l1.setEditorial("Tusquets");
            l1.setPaginas(498);

            //CREA UN ARRAY DE OBRAS PARA GUARDAR LOS LIBROS Y HACER LAS RELACIONES
            Obra o1[] = new Obra[2];
            o1[0] = l1;
            o1[1] = l2;

            //GUARDA LAS RELACIONES DEL AUTOR CON LOS LIBROS QUE HA ESCRITO
            a1.setEscribe(o1);
            
            //EJECUTA UN COMMIT PARA MATERIALIZAR LAS PETICIONES
            db.commit();
            
            //CIERRA LA BASE DE DATOS
            db.close();

        } catch (MtException mte) {
            System.out.println("MtException : " + mte.getMessage());
        }
    }
    }
