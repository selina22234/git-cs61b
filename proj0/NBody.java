public class NBody{
	public static String imageToDraw = "images/starfield.jpg";

	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		readRadius(filename);
		readPlanets(filename);
		StdDraw.setScale(-readRadius(filename), readRadius(filename));/*??*/
		StdDraw.picture(0,0,imageToDraw);
		for(int i=0;i<readPlanets(filename).length;i++){
			readPlanets(filename)[i].draw();
		}

		StdDraw.enableDoubleBuffering();
		double time=0;
		Planet[] p=new Planet[readPlanets(filename).length];
		p=readPlanets(filename);
		while(time<T){
			double[] xForces=new double[p.length];
			double[] yForces=new double[p.length];
			for(int i=0;i<p.length;i++){
				xForces[i]=p[i].calcNetForceExertedByX(p);
				yForces[i]=p[i].calcNetForceExertedByY(p);
			}
			for(int i=0;i<p.length;i++){
				p[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,imageToDraw);
			for(int i=0;i<p.length;i++){
				p[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);		
			time+=dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
    			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName); 
    	}
	}

	public static double readRadius(String s){
		In in=new In(s);
		int n=in.readInt();
		double radius=in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String s){
		In in=new In(s);
		int n=in.readInt();
		double radius=in.readDouble();
		Planet[] arrp=new Planet[n];
		for(int i=0;i<n;i++){
			double xxPos=in.readDouble();
			double yyPos=in.readDouble();
			double xxVel=in.readDouble();
			double yyVel=in.readDouble();
			double mass=in.readDouble();
			String imgFileName=in.readString();

			arrp[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}
		return arrp;
	}
} 