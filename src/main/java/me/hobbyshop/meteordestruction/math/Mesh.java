package me.hobbyshop.meteordestruction.math;

import lombok.Getter;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL30.*;

@Getter
public class Mesh {

    private Vertex[] vertices;
    private int[] indices;

    private int vao;
    private int vbo,
                ibo;

    public Mesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public void build() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] vertexData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; ++i) {
            vertexData[i * 3] = vertices[i].getPosition().getX();
            vertexData[i * 3 + 1] = vertices[i].getPosition().getY();
            vertexData[i * 3 + 2] = vertices[i].getPosition().getZ();
        }
        vertexBuffer.put(vertexData).flip();

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

}
