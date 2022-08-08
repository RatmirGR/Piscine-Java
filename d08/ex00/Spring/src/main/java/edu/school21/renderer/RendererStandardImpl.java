package edu.school21.renderer;

import edu.school21.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer{
    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor){
        this.preProcessor = preProcessor;
    }

    @Override
    public void printMsgToConsole(String text) {
        System.out.print(preProcessor.messagePreprocessing(text));
    }
}
