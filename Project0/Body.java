public class Body {
    double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double distance = Math.pow((Math.pow(dx,2) + Math.pow(dy,2)), 0.5);
        return distance;  
    }
    
    public double calcForceExertedBy(Body b) {
        double Force = 6.67 * Math.pow(10, -11) * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
        return Force;
    }

    public double calcForceExertedByX(Body b) {
        double dx = this.xxPos - b.xxPos;
        if (dx < 0) {dx = -dx;}
        double ForceX = this.calcForceExertedBy(b) * dx / this.calcDistance(b);
        return ForceX;
    }

    public double calcForceExertedByY(Body b) {
        double dy = this.yyPos - b.yyPos;
        if (dy < 0) {dy = -dy;}
        double ForceY = this.calcForceExertedBy(b) * dy / this.calcDistance(b);
        return ForceY;
    }

    public double calcNetForceExertedByX(Body[] Bodys) {
        double netForceX = 0;
        for (int i=0; i<3; i++) {
            if (Bodys[i] != this) {
                netForceX += this.calcForceExertedByX(Bodys[i]);
            }
        }
        return netForceX;
    }   
    
    public double calcNetForceExertedByY(Body[] Bodys) {
        double netForceY = 0;
        for (int i=0; i<3; i++) {
            if (Bodys[i] != this) {
                netForceY += this.calcForceExertedByY(Bodys[i]);
            }
        }
        return netForceY;    
    }

    public void update(double dt, double fx, double fy) {
        double aNetX = fx / mass;
        double aNetY = fy / mass;
        xxVel += dt * aNetX;
        yyVel += dt * aNetY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    
    public static String imageToDraw = "images/starfield.jpg";
    
    public void drawBody() {       
        // StdDraw.setPenRadius(1);
        StdDraw.picture(xxPos / (2.5e+12), yyPos / (2.5e+12), "images/" + imgFileName, 0.05, 0.05);     
    }
}
