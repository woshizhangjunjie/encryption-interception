package paser.handlerCore.encryptionStrategy.strategyAnalysis;

import paser.handlerAbstract.encryption.SmartIntercept;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.concreteStrategy.BCRYPT;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.concreteStrategy.MD5;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.concreteStrategy.SHA256;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.context.SmartContext;

import java.util.ArrayList;
import java.util.List;

public class SmartPaser {

    private static List<SmartContext> algorithmContexts = new ArrayList<>();

    static {
        algorithmContexts.add(new SmartContext(SmartType.MD5, new MD5()));
        algorithmContexts.add(new SmartContext(SmartType.BCRYPT, new BCRYPT()));
        algorithmContexts.add(new SmartContext(SmartType.SHA256, new SHA256()));
    }

    public String options(SmartType algorithmType, String original, String salt,Integer num) {
        SmartIntercept algorithmIntercept = null;
        for (SmartContext algorithmContext : algorithmContexts) {
            if (algorithmContext.options(algorithmType)) {
                algorithmIntercept = algorithmContext.getAlgorithmIntercept();
                break;
            }
        }
        return algorithmIntercept.proceed(original, salt,num);
    }
}
