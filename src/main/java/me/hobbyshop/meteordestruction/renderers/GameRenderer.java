package me.hobbyshop.meteordestruction.renderers;

import me.hobbyshop.meteordestruction.math.Mesh;

import static org.lwjgl.opengl.GL30.*;

public class GameRenderer {

    public void renderMesh(Mesh mesh) {
        glBindVertexArray(mesh.getVao());
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.getIbo());

        glDrawElements(GL_TRIANGLES, mesh.getIndices().length, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }

}
