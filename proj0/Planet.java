class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double temp = Math.pow(p.xxPos - this.xxPos, 2) + Math.pow(p.yyPos - this.yyPos, 2);
        return Math.sqrt(temp);
    }

    public double calcForceExertedBy(Planet p) {
        return G * p.mass * this.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * Math.sqrt(Math.pow((p.xxPos - this.xxPos) / calcDistance(p), 2));
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * Math.sqrt(Math.pow((p.yyPos - this.yyPos) / calcDistance(p), 2));
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double totalForce = 0;
        for (int i = 0; i < planets.length; i++) {
            double singleForce;
            if (planets[i].equals(this)) {
                singleForce = 0;
            }
            else {
                singleForce = calcForceExertedByX(planets[i]);
                totalForce += singleForce;
            }
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double totalForce = 0;
        for (int i = 0; i < planets.length; i++) {
            double singleForce;
            if (planets[i].equals(this)) {
                singleForce = 0;
            }
            else {
                singleForce = calcForceExertedByY(planets[i]);
                totalForce += singleForce;
            }
        }
        return totalForce;
    }

    public void update(double time, double XForce, double YForce) {
        double aX = XForce / this.mass;
        double aY = YForce / this.mass;

        this.xxVel += aX * time;
        this.yyVel += aY * time;

        this.xxPos += xxVel * time;
        this.yyPos += yyVel * time;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        StdDraw.show();
    }
}