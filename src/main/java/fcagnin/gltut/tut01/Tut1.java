package fcagnin.gltut.tut01;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

import org.lwjgl.BufferUtils;

import fcagnin.gltut.LWJGLWindow;


/**
 * Visit https://github.com/rosickteam/OpenGL for project info, updates and license terms.
 * 
 * I. The Basics
 * Chapter 1. Hello, Triangle! 
 * http://www.arcsynthesis.org/gltut/Basics/Tutorial%2001.html
 * @author integeruser
 */
public class Tut1 extends LWJGLWindow {
	
	public static void main(String[] args) {
		new Tut1().start();
	}

	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		
	@Override
	protected void init() {
		initializeProgram();
		initializeVertexBuffer(); 

		vao = glGenVertexArrays();
		glBindVertexArray(vao);
	}
	
		
	@Override
	protected void display() {	
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);

		glUseProgram(theProgram);

		glBindBuffer(GL_ARRAY_BUFFER, positionBufferObject);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 4, GL_FLOAT, false, 0, 0);

		glDrawArrays(GL_TRIANGLES, 0, 3);

		glDisableVertexAttribArray(0);
		glUseProgram(0);
	}
	
	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	private final String strVertexShader = 
			"#version 330 \n" +
			"\n" +
			"layout(location = 0) in vec4 position;\n" +
			"void main()\n" +
			"{\n" +
			"    gl_Position = position;\n" +
			"}";
	private final String strFragmentShader = 
			"#version 330\n" +
			"\n" +
			"out vec4 outputColor;\n" +
			"void main()\n" +
			"{\n" +
			"   outputColor = vec4(1.0f, 1.0f, 1.0f, 1.0f);\n" +
			"}";
	
	private int theProgram;
	private int vao;

	
	private void initializeProgram() {	
		ArrayList<Integer> shaderList = new ArrayList<>();
		shaderList.add(createShader(GL_VERTEX_SHADER,	strVertexShader));
		shaderList.add(createShader(GL_FRAGMENT_SHADER,	strFragmentShader));

		theProgram = createProgram(shaderList);
		
	    for (Integer shader : shaderList) {
	    	glDeleteShader(shader);
		}
	}

	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	private final float vertexPositions[] = {
			0.75f,  0.75f, 0.0f, 1.0f,
			0.75f, -0.75f, 0.0f, 1.0f,
		   -0.75f, -0.75f, 0.0f, 1.0f};
	
	private int positionBufferObject;
	
	
	private void initializeVertexBuffer() {
		FloatBuffer vertexPositionsBuffer = BufferUtils.createFloatBuffer(vertexPositions.length);
		vertexPositionsBuffer.put(vertexPositions);
		vertexPositionsBuffer.flip();
        
		positionBufferObject = glGenBuffers();	       
		glBindBuffer(GL_ARRAY_BUFFER, positionBufferObject);
	    glBufferData(GL_ARRAY_BUFFER, vertexPositionsBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	private int createShader(int shaderType, String shaderFile) {
        int shader = glCreateShader(shaderType);
        glShaderSource(shader, shaderFile);

        glCompileShader(shader);

        int status = glGetShaderi(shader, GL_COMPILE_STATUS);
        if (status == GL_FALSE) {
    		int infoLogLength = glGetShaderi(shader, GL_INFO_LOG_LENGTH);

    		String infoLog = glGetShaderInfoLog(shader, infoLogLength);

    		String strShaderType = null;
			switch (shaderType) {
			case GL_VERTEX_SHADER:
				strShaderType = "vertex";
				break;
				
			case GL_GEOMETRY_SHADER:
				strShaderType = "geometry";
				break;
				
			case GL_FRAGMENT_SHADER:
				strShaderType = "fragment";
				break;
			}

    		System.err.printf("Compile failure in %s shader:\n%s\n", strShaderType, infoLog);
        }
        
		return shader;
	}
	
	
	private int createProgram(ArrayList<Integer> shaderList) {		
		int program = glCreateProgram();

		for (Integer shader : shaderList) {
			glAttachShader(program, shader);
		}

		glLinkProgram(program);
		
		int status = glGetProgrami(program, GL_LINK_STATUS);
		if (status == GL_FALSE) {
			int infoLogLength = glGetProgrami(program, GL_INFO_LOG_LENGTH);

			String strInfoLog = glGetProgramInfoLog(program, infoLogLength);
			System.err.printf("Linker failure: %s\n", strInfoLog);
		}
		
		for (Integer shader : shaderList) {
			glDetachShader(program, shader);
		}

		return program;
	}
}