public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet P) {
        return Math.sqrt((xxPos - P.xxPos) * (xxPos - P.xxPos) + (yyPos - P.yyPos) * (yyPos - P.yyPos));
    }

    public double calcForceExertedBy(Planet P) {
        return G * mass * P.mass / calcDistance(P) / calcDistance(P);
    }

    public double calcForceExertedByX(Planet P) {
        if (xxPos > P.xxPos) {
            return -calcForceExertedBy(P) * (Math.abs((xxPos - P.xxPos) / calcDistance(P)));
        } else {
            return calcForceExertedBy(P) * (Math.abs((xxPos - P.xxPos) / calcDistance(P)));
        }
    }

    public double calcForceExertedByY(Planet P) {
        if (yyPos > P.yyPos) {
            return -calcForceExertedBy(P) * (Math.abs((yyPos - P.yyPos) / calcDistance(P)));
        } else {
            return calcForceExertedBy(P) * (Math.abs((yyPos - P.yyPos) / calcDistance(P)));
        }
    }

    public double calcNetForceExertedByX(Planet[] PArray) {
        double currentNetForceX = 0;
        for (Planet planet : PArray) {
            if (planet.equals(this)) {
                continue;
            }
            currentNetForceX += calcForceExertedByX(planet);
        }
        return currentNetForceX;
    }
    public double calcNetForceExertedByY(Planet[] PArray) {
        double currentNetForceY = 0;
        for (Planet planet : PArray) {
            if (planet.equals(this)) {
                continue;
            }
            currentNetForceY += calcForceExertedByY(planet);
        }
        return currentNetForceY;
    }

    public void update(double dt,double fX,double fY){
        xxVel += fX / mass * dt;
        yyVel += fY / mass * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,imgFileName);
    }

}











