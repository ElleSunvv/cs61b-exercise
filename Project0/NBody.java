
public class NBody {
	public static Body[] readBodies(String file) {
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        Body[] Bodies = new Body[5];
        for (int i = 0; i < 5; i++) {
            double xxPo = in.readDouble();
            double yyPo = in.readDouble();
            double xxVe = in.readDouble();
            double yyVe = in.readDouble();
            double mas = in.readDouble();
            String im = in.readString();       
            Bodies[i] = new Body(xxPo, yyPo, xxVe, yyVe, mas, im);
        }
        return Bodies;
    }

    public static double readRadius(String file) {
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static int readNum(String file) {
        In in = new In(file);
        int num = in.readInt();
        return num;
    }

    public static String imageToDraw = "images/starfield.jpg";

    public static void main (String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);
        int num = readNum(filename);

        StdDraw.picture(0.5, 0.5, imageToDraw);
        for (int i = 0; i < num; i++) {
            bodies[i].drawBody();
        }    

        StdDraw.enableDoubleBuffering();
        StdDraw.show();

        double time = 0;
        int milliseconds = 10;
        while (time <= T) {
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            StdDraw.clear();
            StdDraw.picture(0.5, 0.5, imageToDraw);
            for (int i = 0; i < num; i++) {
                    xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                    yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
                }
            
            for (int i = 0; i < num; i++) {               
                bodies[i].update(dt, xForces[i], yForces[i]);
                }
            
            for (int i = 0; i < num; i++) {
                bodies[i].drawBody();
            } 
            StdDraw.show();
            StdDraw.pause(milliseconds);
            time += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i  = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %12s\n",
                        bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel, bodies[i].yyVel,
                        bodies[i].mass, bodies[i].imgFileName);
        }
    }
}