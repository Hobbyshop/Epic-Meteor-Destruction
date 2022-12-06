package me.hobbyshop.meteordestruction.graphics;

import me.hobbyshop.meteordestruction.utility.Logger;

import java.io.*;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private String vertSource,
                    fragSource;

    private int vertID,
                fragID,
                programID;

    public Shader(String vertPath, String fragPath) {
        this.vertSource = loadShaderSource(vertPath);
        this.fragSource = loadShaderSource(fragPath);
    }

    public void create() {
        programID = glCreateProgram();
        vertID = glCreateShader(GL_VERTEX_SHADER);
        fragID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertID, vertSource);
        glCompileShader(vertID);
        if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Vertex Shader: " + glGetShaderInfoLog(vertID));
            return;
        }

        glShaderSource(fragID, fragSource);
        glCompileShader(fragID);
        if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Fragment Shader: " + glGetShaderInfoLog(fragID));
            return;
        }

        glAttachShader(programID, vertID);
        glAttachShader(programID, fragID);
        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Program Linking: " + glGetProgramInfoLog(programID));
            return;
        }

        glValidateProgram(programID);
        if (glGetProgrami(programID, GL_VALIDATE_STATUS) == GL_FALSE) {
            System.err.println("Program Validation: " + glGetProgramInfoLog(programID));
            return;
        }

        glDeleteShader(vertID);
        glDeleteShader(fragID);
    }

    public void destroy() {
        glDeleteProgram(programID);
    }

    public void bind() {
        glUseProgram(programID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    private String loadShaderSource(String path) {
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            Logger.error("Unable to read Shader file at " + path);
            return null;
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }

            return builder.toString();

        } catch (IOException e) {
            Logger.error("Unable to read Shader file at " + path);
            return null;
        }

    }

}
