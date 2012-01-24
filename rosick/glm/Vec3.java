package rosick.glm;


/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and license terms.
 * 
 * @author integeruser, xire-
 */
public class Vec3 extends Vec {

	public Vec3() {
		vector = new float[3];
	}
	
	public Vec3(float x, float y, float z) {
		vector = new float[3];
		vector[X] = x;
		vector[Y] = y;
		vector[Z] = z;
	}
	
	public Vec3(Vec3 vec) {
		vector = new float[3];
		vector[X] = vec.vector[X];
		vector[Y] = vec.vector[Y];
		vector[Z] = vec.vector[Z];
	}
	
	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */	
	
	public Vec3 add(Vec3 vec) {
		vector[X] += vec.vector[X];
		vector[Y] += vec.vector[Y];
		vector[Z] += vec.vector[Z];
		
		return this;
	}
	
	public Vec3 sub(Vec3 vec) {
		vector[X] -= vec.vector[X];
		vector[Y] -= vec.vector[Y];
		vector[Z] -= vec.vector[Z];
		
		return this;
	}
	
	public Vec3 mul(Vec3 vec) {
		vector[X] *= vec.vector[X];
		vector[Y] *= vec.vector[Y];
		vector[Z] *= vec.vector[Z];
		
		return this;
	}
	
	
	public Vec3 scale(float scalar) {
		vector[X] *= scalar;
		vector[Y] *= scalar;
		vector[Z] *= scalar;
		
		return this;
	}
	

	public Vec3 negate() {
		vector[X] = -vector[X];
		vector[Y] = -vector[Y];
		vector[Z] = -vector[Z];

		return this;
	}
	
	
	@Override
	public String toString() {
		return "X: " + vector[X] + ", Y: " + vector[Y] + ", Z: " + vector[Z];
	}

	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */	
	
	public static Vec3 add(Vec3 a, Vec3 b) {
		Vec3 vec = new Vec3(a);
		
		return vec.add(b);
	}
	
	public static Vec3 sub(Vec3 a, Vec3 b) {
		Vec3 vec = new Vec3(a);

		return vec.sub(b);
	}	
	
	public static Vec3 mul(Vec3 a, Vec3 b) {	
		Vec3 vec = new Vec3(a);
		
		return vec.mul(b);
	}
	
	
	public static Vec3 scale(Vec3 a, float scalar) {
		Vec3 vec = new Vec3(a);
		
		return vec.scale(scalar);
	}
	

	public static Vec3 negate(Vec3 a) {
		Vec3 vec = new Vec3(a);
		
		return vec.negate();
	}
}