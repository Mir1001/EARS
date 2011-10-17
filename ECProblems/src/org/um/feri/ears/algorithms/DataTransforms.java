/**
 * Insert data
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
package org.um.feri.ears.algorithms;

import java.lang.reflect.Array;

import org.um.feri.ears.export.data.Author;
import org.um.feri.ears.export.data.Player;
import org.um.feri.ears.export.data.Rating;

/**
 * @author Administrator
 *
 */
public class DataTransforms {
    public static Player fromPlayerAlgorithm(Algorithm a) {
        Player p = new Player();
        p.id_version = a.ai.getVersionAcronym();
        p.description = a.ai.getVersionDescription();
        p.source = a.ai.getPaperBib();
        p.info =   a.ai.getParameters().toString(); //TODO
        p.sourceCode = new Author();
        p.sourceCode.email = a.au.getEmail();
        p.sourceCode.firstName = a.au.getFirstName();
        p.sourceCode.lastName = a.au.getLastName();
        p.sourceCode.nickName = a.au.getNickName();
        p.sourceCode.info = a.au.getInfo();
        return p;
    }
    public static void setOldRating(Player p, double r, double rd, double rv) {
        p.oldRating = new Rating();
        p.oldRating.rating = r;
        p.oldRating.RD = rd;
        p.oldRating.rv = rv;
    }
    public static void setNewRating(Player p, double r, double rd, double rv) {
        p.newRating = new Rating();
        p.newRating.rating = r;
        p.newRating.RD = rd;
        p.newRating.rv = rv;
    }
    
}
