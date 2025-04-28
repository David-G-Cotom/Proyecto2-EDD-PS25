/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.matrix;

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
