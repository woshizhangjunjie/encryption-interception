package paser.handlerCore.encryptionStrategy.strategyAnalysis.context;

import paser.handlerAbstract.encryption.SmartIntercept;
import paser.handlerCore.encryptionStrategy.strategyAnalysis.SmartType;

public class SmartContext {

    private SmartType smartType;

    private SmartIntercept smartIntercept;

    public SmartContext(SmartType smartType, SmartIntercept algorithmIntercept){
        this.smartType = smartType;
        this.smartIntercept = algorithmIntercept;
    }

    public SmartIntercept getAlgorithmIntercept() {
        return smartIntercept;
    }

    public boolean options(SmartType smartType){
       return this.smartType.equals(smartType);
    }
}
