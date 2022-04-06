public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos=xP;yyPos=yP;xxVel=xV;yyVel=yV;mass=m;imgFileName=img;
	}
	public Planet(Planet b){
		this.xxPos=b.xxPos;
		this.yyPos=b.yyPos;
		this.xxVel=b.xxVel;
		this.yyVel=b.yyVel;
		this.mass=b.mass;
		this.imgFileName=b.imgFileName;
	}

	public double calcDistance(Planet b2){
		double dx,dy,r;
		dx=b2.xxPos-this.xxPos;
		dy=b2.yyPos-this.yyPos;
		r=Math.sqrt(dx*dx+dy*dy);
		return r;
	}

	static final double G=6.67E-11;
	public double calcForceExertedBy(Planet b2){
		double r=this.calcDistance(b2);
		double f=G*this.mass*b2.mass/(r*r);
		return f;
	}

	public double calcForceExertedByX(Planet b2){
		double fx=this.calcForceExertedBy(b2)*(b2.xxPos-this.xxPos)/this.calcDistance(b2);
		return fx;
	}

	public double calcForceExertedByY(Planet b2){
		double fy=this.calcForceExertedBy(b2)*(b2.yyPos-this.yyPos)/this.calcDistance(b2);
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] arrPlanet){
		double fsx=0;
		for(int i=0;i<arrPlanet.length;i++){
			if(this.equals(arrPlanet[i])){
				continue;
			}
			fsx=fsx+this.calcForceExertedByX(arrPlanet[i]);
		}
		return fsx;
	}

	public double calcNetForceExertedByY(Planet[] arrPlanet){
		double fsy=0;
		for(int i=0;i<arrPlanet.length;i++){
			if(this.equals(arrPlanet[i])){
				continue;
			}
			fsy=fsy+this.calcForceExertedByY(arrPlanet[i]);
		}
		return fsy;
	}

	public void update(double dt,double fx,double fy){
		double ax=fx/this.mass;
		double ay=fy/this.mass;		
		this.xxVel=this.xxVel+dt*ax;
		this.yyVel=this.yyVel+dt*ay;
		this.xxPos=this.xxPos+this.xxVel*dt;
		this.yyPos=this.yyPos+this.yyVel*dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}
}