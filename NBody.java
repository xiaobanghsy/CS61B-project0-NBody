public class NBody {
    public static double readRadius(String s){
        In in = new In(s);
        int first_tmp = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String s){
        In in = new In(s);
        int numOfPlanet = in.readInt();
        double a = in.readDouble();
        Planet []planet = new Planet[numOfPlanet];
        for(int i = 0; i < numOfPlanet; i ++){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planet[i] = new Planet(xp, yp, xv, yv, m, img);
            //必须使用构造函数，而不是直接调用planet[i].，可能是因为java语言比较安全，在
            //初始化的时候，每一个planet[i]都指向null，尚未实例化。
        }
        return planet;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        // String filename = "./data/planets.txt";
        double universeRadius = NBody.readRadius(filename);
        Planet[] planetArray = NBody.readPlanets(filename);
        StdDraw.setScale(-universeRadius,universeRadius);
        StdDraw.clear();
        StdDraw.picture(0,0,"./images/starfield.jpg");

        int planetLength = planetArray.length;
        for (Planet planet : planetArray) {
            planet.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time = 0;
        for (; time <= T; time += dt){
            double[] xForces = new double[planetLength];
            double[] yForces = new double[planetLength];
            for (int i = 0; i < planetLength; i++) {
                xForces[i] = planetArray[i].calcNetForceExertedByX(planetArray);
                yForces[i] = planetArray[i].calcNetForceExertedByY(planetArray);
            }
            for (int i = 0; i < planetLength; i++) {
                planetArray[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,"./images/starfield.jpg");
            for (Planet planet : planetArray) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

    }


}

