package com.bsc36.project11cs.application.services.dancinglinks;

import java.util.List;


public class DancingLinksRunnable implements Runnable {
        @Override
        public void run() { // called when thread is started

                CoverMatrixGenerator coverMatrixGenerator = new CoverMatrixGenerator("Parcels");
                boolean[][] exactCoverMatrix = coverMatrixGenerator.getExactCoverMatrix();
                List<Integer> rowTypes = coverMatrixGenerator.getRowTypes();
                DancingLinks dancingLinks = new DancingLinks(exactCoverMatrix, rowTypes);
                dancingLinks.startSolve();
        }
}