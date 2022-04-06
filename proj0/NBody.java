public class NBody {
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];

        double radius = readRadius(fileName); // 读取宇宙的半径
        String BGPath = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, BGPath);
        StdDraw.show();

        //画出天体们
        Planet[] planets = readPlanets(fileName);
        for(Planet p : planets) {
            p.draw();
        }

        // 创建动画
        StdDraw.enableDoubleBuffering();
        int time = 0;
        while (time <= T) {
            double[] xForces = new double[5];
            double[] yForces = new double[5];

            // 计算两个方向的力并储存在数组中
            for (int i = 0; i < planets.length; i++) {
                double singleXForce = planets[i].calcNetForceExertedByX(planets);
                double singleYForce = planets[i].calcNetForceExertedByY(planets);
                xForces[i] = singleXForce;
                yForces[i] = singleYForce;
            }

            // 更新天体们的位置、速度、加速度等
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // 把背景重新画一遍
            StdDraw.picture(0, 0, "images/starfield.jpg");
            // 把天体们重新画一遍
            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }


    }

    public static double readRadius(String filename) {
        In in = new In(filename);
        int planetNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int planetNum = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[planetNum];

        for (int i = 0; i < planetNum; i++) {
            planets[i] = new Planet(0, 0, 0, 0, 0, null);
            planets[i].xxPos = in.readDouble();
            planets[i].yyPos = in.readDouble();
            planets[i].xxVel = in.readDouble();
            planets[i].yyVel = in.readDouble();
            planets[i].mass = in.readDouble();
            planets[i].imgFileName = in.readString();
        }
        return planets;
    }
}
