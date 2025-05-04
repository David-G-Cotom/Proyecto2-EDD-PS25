/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

import java.util.Arrays;

/**
 *
 * @author Carlos Cotom
 */
public enum StreetType {
    
    INTERSECCION("""
                _| |_
                _   _
                 | | 
                 """.stripIndent()),
    CRUCE_ESQUINA_1("""
                     ____
                    |  __
                    | |  
                    """.stripIndent()),
    CRUCE_ESQUINA_2("""
                    ____ 
                    __  |
                      | |
                    """.stripIndent()),
    CRUCE_ESQUINA_3("""
                    | |__
                    |    
                    |____
                    """.stripIndent()),
    CRUCE_ESQUINA_4("""
                    __| |
                        |
                    ____|
                    """.stripIndent()),
    CRUCE_NORTE("""
                _____
                _   _
                 | | 
                """.stripIndent()),
    CRUCE_DERECHA("""
                __| |
                __  |
                  | |
                  """.stripIndent()),
    CRUCE_SUR("""
            _| |_
                 
            _____
              """.stripIndent()),
    CRUCE_IZQUIERDA("""
                    | |__
                    |  __
                    | |  
                    """.stripIndent()),
    INTERSECCION_SEMAFORO("""
                _| |_
                _ & _
                 | | 
                 """.stripIndent()),
    CRUCE_ESQUINA_1_SEMAFORO("""
                     ____
                    |& __
                    | |  
                    """.stripIndent()),
    CRUCE_ESQUINA_2_SEMAFORO("""
                    ____ 
                    __ &|
                      | |
                    """.stripIndent()),
    CRUCE_ESQUINA_3_SEMAFORO("""
                    | |__
                    |&   
                    |____
                    """.stripIndent()),
    CRUCE_ESQUINA_4_SEMAFORO("""
                    __| |
                       &|
                    ____|
                    """.stripIndent()),
    CRUCE_NORTE_SEMAFORO("""
                _____
                _ & _
                 | | 
                """.stripIndent()),
    CRUCE_DERECHA_SEMAFORO("""
                __| |
                __ &|
                  | |
                  """.stripIndent()),
    CRUCE_SUR_SEMAFORO("""
            _| |_
              &  
            _____
              """.stripIndent()),
    CRUCE_IZQUIERDA_SEMAFORO("""
                    | |__
                    |& __
                    | |  
                    """.stripIndent()),
    INTERSECCION_NO_LIBRE("""
                _| |_
                _ # _
                 | | 
                 """.stripIndent()),
    CRUCE_ESQUINA_1_NO_LIBRE("""
                     ____
                    |# __
                    | |  
                    """.stripIndent()),
    CRUCE_ESQUINA_2_NO_LIBRE("""
                    ____ 
                    __ #|
                      | |
                    """.stripIndent()),
    CRUCE_ESQUINA_3_NO_LIBRE("""
                    | |__
                    |#   
                    |____
                    """.stripIndent()),
    CRUCE_ESQUINA_4_NO_LIBRE("""
                    __| |
                       #|
                    ____|
                    """.stripIndent()),
    CRUCE_NORTE_NO_LIBRE("""
                _____
                _ # _
                 | | 
                """.stripIndent()),
    CRUCE_DERECHA_NO_LIBRE("""
                __| |
                __ #|
                  | |
                  """.stripIndent()),
    CRUCE_SUR_NO_LIBRE("""
            _| |_
              #  
            _____
              """.stripIndent()),
    CRUCE_IZQUIERDA_NO_LIBRE("""
                    | |__
                    |# __
                    | |  
                    """.stripIndent());
    
    private String template;
    private String[] lines;

    private StreetType(String template) {
        this.template = template;
        this.lines = this.template.split("\n");
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String[] getLines() {
        return Arrays.copyOf(lines, lines.length);
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }
    
}
