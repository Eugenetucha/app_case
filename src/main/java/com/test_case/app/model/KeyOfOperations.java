package com.test_case.app.model;

public enum KeyOfOperations {
    MODEL_COLOR{
        public String getValue(){return "modelColor";}
    },
    MODEL_PRICE{
        public String getValue(){return "modelPrice";}
    },
    MODEL_NAME{
        public String getValue(){return "modelName";}
    },
    MODEL_SERIAL_NUMBER{
        public String getValue(){return "modelSerialNumber";}
    },
    MODEL_SIZE{
        public String getValue(){return "modelSize";}
    },
    MODEL_AVAILABILITY{
        public String getValue(){return "modelAvailability";}
    };
    public abstract String getValue();
}
