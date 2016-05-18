package i5d.plugin;
//
// z_Image5D_test.java
//

/*
Image5D plugins for 5-dimensional image stacks in ImageJ.

Copyright (c) 2010, Joachim Walter and ImageJDev.org.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

/*
 * Created on 26.03.2005
 */

/**
 * @author Joachim Walter
 *
 * Tests the Image5D class, which extends 
 * ImagePlus to 5 dimensions instead of 3.
 */

import i5d.Image5D;
import i5d.gui.Image5DCanvas;
import i5d.gui.Image5DLayout;
import ij.IJ;
import ij.ImageStack;
import ij.plugin.PlugIn;
import ij.process.ByteProcessor;

import java.awt.Dimension;
import java.awt.Polygon;

public class z_Image5D_test implements PlugIn {

	@Override
	public void run(final String arg) {
//		byte[]pix = new byte[40000];
//		ImageStack is = NewImage.createFloatImage("test", 200, 200, 10, NewImage.FILL_RAMP).getStack();
//		is.deleteLastSlice();
//		is.addSlice("", (Object)pix);
		// ByteProcessor bp = (ByteProcessor)NewImage.createByteImage("test", 200,
		// 200, 1, NewImage.FILL_RAMP).getProcessor();

//		ImagePlus imgP = new ImagePlus("test img plus", is);
//		imgP.setSlice(3);
//		imgP.setProcessor("", new ShortProcessor(200,200));
//		imgP.show();

//		Image5D img = new Image5D("test", is);

//		Image5D img = new Image5D("test", ImagePlus.GRAY8, 500, 200, 1, 1, 1, false);

//		Image5D img = new Image5D("test", ImagePlus.GRAY8, new int[] {200,200,2,2,2}, true);
//
//		for (int i=0; i<2; ++i) {
//			img.getChannelCalibration(i+1).setLabel("Ch-"+(i+1));
//			for (int j=0; j<2; ++j) {
//				for (int k=0; k<2; ++k) {
//					Polygon p = new Polygon(new int[] {10+j*50, 100+i*100, 100+k*100, 10}, new int[] {10, 10, 200, 200}, 4);
//					img.setCurrentPosition(0,0,i, j, k);
//					img.getProcessor().setValue(127);
//					img.getProcessor().fillPolygon(p);
//				}
//			}
//		}

		final Dimension imgSize = new Dimension(688, 520);
		final ImageStack stack = new ImageStack(imgSize.width, imgSize.height);
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				for (int k = 0; k < 3; ++k) {
					final Polygon p =
						new Polygon(new int[] { 10 + j * 50, 100 + i * 100, 100 + k * 100,
							10 }, new int[] { 10, 10, 200, 200 }, 4);
					final ByteProcessor proc =
						new ByteProcessor(imgSize.width, imgSize.height);
					proc.setValue(127);
					proc.fillPolygon(p);
					stack.addSlice("ch" + k + " z" + j + " t" + i, proc);
				}
			}
		}

		final Image5D img = new Image5D("test", stack, 3, 2, 2);

		img.setCurrentPosition(0, 0, 0, 0, 0);
		img.show();
		final Image5DCanvas can = new Image5DCanvas(img.getChannelImagePlus(1));
		img.getWindow().add(can, Image5DLayout.CANVAS);
		img.getWindow().add(new Image5DCanvas(img.getChannelImagePlus(2)),
			Image5DLayout.CANVAS);
		img.getWindow().add(new Image5DCanvas(img.getChannelImagePlus(3)),
			Image5DLayout.CANVAS);
		img.getWindow().pack();

		IJ.wait(2000);
		img.getWindow().remove(can);
		img.getWindow().pack();

	}

}