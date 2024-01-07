    package com.example.demo.entities;

    public enum OperationType {

        deposit("deposit"),
        withdraw("withdraw"),
        sale("sale"),
        close("close"),
        start("start");


        private final String value;

        OperationType(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }

        public static OperationType fromValue(String value){
            for(OperationType type: values()){
                if(type.value.equalsIgnoreCase(value)){
                    return type;
                }
            }

            throw new IllegalArgumentException("Invalid transaction type: "+ value);
        }


    }
