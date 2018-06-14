package com.lak.create;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * zxing生成码图
 * @author lxh
 */
public enum ZxingBuildCodeUtils {
	Instance;

	private static final int WHITE = 0xFFFFFFFF;
	private static final int BLACK = 0xFF000000;

	/**
	 * 制作QR码
	 * @param content QR码表达信息
	 * @param width 码图宽
	 * @param height 码图高
	 * @return 返回Bitmap对象,用于在ImageView上显示
	 */
	public Bitmap buildQRCodeImage(String content, int width, int height) {
		Bitmap bitmap = null;
		if (!TextUtils.isEmpty(content)) {
			try {
				Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
				hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
				hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
				hints.put(EncodeHintType.MARGIN, 2);
				// 图像数据转换，使用了矩阵转换
				BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
				int[] pixels = new int[width * height];
				// 下面这里按照二维码的算法，逐个生成二维码的图片，
				// 两个for循环是图片横列扫描的结果
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						if (bitMatrix.get(x, y)) {
							pixels[y * width + x] = BLACK;
						} else {
							pixels[y * width + x] = WHITE;
						}
					}
				}
				// 生成二维码图片的格式，使用ARGB_8888
				bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
				bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
			} catch (WriterException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}

	/**
	 * 制作CODE_128的条形码
	 * @param content 条形码表达信息
	 * @param width 宽度
	 * @param height 高度
	 * @return
	 */
	public Bitmap buildCODE_128(String content, int width, int height) throws IllegalArgumentException, WriterException {
		return buildBarcodeImage(content, BarcodeFormat.CODE_128, width, height);
	}

	/**
	 * 制作条形码
	 * @param content 条形码表达信息
	 * @param format 条形码 编码格式
	 * @param desiredWidth 希望宽度
	 * @param desiredHeight 希望高度
	 * @return
	 */
	public Bitmap buildBarcodeImage(String content, BarcodeFormat format, int desiredWidth, int desiredHeight) throws IllegalArgumentException, WriterException {
		Bitmap bitmap = null;
		if (!TextUtils.isEmpty(content)) {
			MultiFormatWriter writer = new MultiFormatWriter();
			BitMatrix result = writer.encode(content, format, desiredWidth, desiredHeight, null);
			int width = result.getWidth();
			int height = result.getHeight();
			int[] pixels = new int[width * height];
			// All are 0, or black, by default
			for (int y = 0; y < height; y++) {
				int offset = y * width;
				for (int x = 0; x < width; x++) {
					pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
				}
			}
			// 生成条形码图片的格式，使用ARGB_8888
			bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		}
		return bitmap;
	}

}
