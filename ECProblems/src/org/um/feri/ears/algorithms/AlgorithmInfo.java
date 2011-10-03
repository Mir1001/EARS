package org.um.feri.ears.algorithms;
/**
 * Data about algorithm, where it is published, what is different, etc...
 * versionAcronym is used in reports.
 * <p>
 * 
 * @author Matej Crepinsek
 * @version 1
 * 
 *          <h3>License</h3>
 * 
 *          Copyright (c) 2011 by Matej Crepinsek. <br>
 *          All rights reserved. <br>
 * 
 *          <p>
 *          Redistribution and use in source and binary forms, with or without
 *          modification, are permitted provided that the following conditions
 *          are met:
 *          <ul>
 *          <li>Redistributions of source code must retain the above copyright
 *          notice, this list of conditions and the following disclaimer.
 *          <li>Redistributions in binary form must reproduce the above
 *          copyright notice, this list of conditions and the following
 *          disclaimer in the documentation and/or other materials provided with
 *          the distribution.
 *          <li>Neither the name of the copyright owners, their employers, nor
 *          the names of its contributors may be used to endorse or promote
 *          products derived from this software without specific prior written
 *          permission.
 *          </ul>
 *          <p>
 *          THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *          "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *          LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *          FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *          COPYRIGHT OWNERS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *          INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *          BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *          LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *          CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *          LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *          ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *          POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public class AlgorithmInfo {
    private String publishedAcronym; // from papers (ES(1,1), PSO, DE, ...)
    private String paperBib; // latex bib format name of the original published
                             // paper
    private String versionAcronym; // if you made some semantic changes compared
                                   // to original
    private String versionDescription; // what is different from original

    public AlgorithmInfo(String publishedName, String paperBib, String versionName, String versionDescription) {
        super();
        this.publishedAcronym = publishedName;
        this.paperBib = paperBib;
        this.versionAcronym = versionName;
        this.versionDescription = versionDescription;
    }

    /**
     * Version name needs to be similar to publishedName name. For example:
     * publishedName PSO my name PSO+ or PSOS, ...
     * 
     * @param versionAcronym
     */
    public AlgorithmInfo(String versionName) {
        super();
        this.versionAcronym = versionName;
    }

    public String getPublishedAcronym() {
        return publishedAcronym;
    }

    public void setPublishedAcronym(String publishedAcronym) {
        this.publishedAcronym = publishedAcronym;
    }

    public String getPaperBib() {
        return paperBib;
    }

    public void setPaperBib(String paperBib) {
        this.paperBib = paperBib;
    }

    public String getVersionAcronym() {
        return versionAcronym;
    }

    public void setVersionAcronym(String versionName) {
        this.versionAcronym = versionName;
    }

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

}
