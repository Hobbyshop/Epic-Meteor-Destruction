package me.hobbyshop.meteordestruction.renderers;

import me.hobbyshop.meteordestruction.graphics.Shader;
import me.hobbyshop.meteordestruction.math.Mesh;

import static org.lwjgl.opengl.GL30.*;

public class GameRenderer {

    public void renderMesh(Mesh mesh, Shader shader) {
        glBindVertexArray(mesh.getVao());
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.getIbo());
        shader.bind();

        glDrawElements(GL_TRIANGLES, mesh.getIndices().length, GL_UNSIGNED_INT, 0);

        shader.unbind();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }

}
