import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ModelPOJOs.Model;
import ModelPOJOs.Point2;
import ModelPOJOs.Point3;

public class OBJParser {
    
    private ArrayList<Point3> vs;
    private ArrayList<Point3> vns;
    private ArrayList<Point2> vts;
    private ArrayList<int[][]> f;

    public OBJParser() {
        this.vs = new ArrayList<>();
        this.vns = new ArrayList<>();
        this.vts = new ArrayList<>();
        this.f = new ArrayList<int[][]>();
    }

    public Model loadModel(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = null;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens[0].equals("v")) {
                vs.add(new Point3(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])));
            }
            else if (tokens[0].equals("vn")) {
                vns.add(new Point3(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])));
            }
            else if (tokens[0].equals("vt")) {
                vts.add(new Point2(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2])));
            }
            else if (tokens[0].equals("f")) {
                int[][] fData = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    int[] fTuple = new int[3];
                    String[] split = tokens[i + 1].split("/");
                    fTuple[0] = Integer.parseInt(split[0]);
                    fTuple[1] = Integer.parseInt(split[1]);
                    fTuple[2] = Integer.parseInt(split[2]);
                    fData[i] = fTuple;
                }
                f.add(fData);
            }
        }
        reader.close();

        return new Model(vs, vns, vts, f);
    }
}