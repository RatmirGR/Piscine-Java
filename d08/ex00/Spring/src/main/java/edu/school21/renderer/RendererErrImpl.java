package edu.school21.renderer;

import edu.school21.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer{
    private final PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor){
        this.preProcessor = preProcessor;
    }

    @Override
    public void printMsgToConsole(String text) {
        System.err.print(preProcessor.messagePreprocessing(text));
    }
}
