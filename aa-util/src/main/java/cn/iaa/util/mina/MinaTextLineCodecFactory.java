package cn.iaa.util.mina;

import java.nio.charset.Charset;

import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

public class MinaTextLineCodecFactory extends TextLineCodecFactory {

	private int length = 4096;
	private String encodingDelimiter;
	private String decodingDelimiter;
	private String charset;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getEncodingDelimiter() {
		return encodingDelimiter;
	}

	public void setEncodingDelimiter(String encodingDelimiter) {
		this.encodingDelimiter = encodingDelimiter;
	}

	public String getDecodingDelimiter() {
		return decodingDelimiter;
	}

	public void setDecodingDelimiter(String decodingDelimiter) {
		this.decodingDelimiter = decodingDelimiter;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public MinaTextLineCodecFactory() {
		super();
		this.setDecoderMaxLineLength(length);
		this.setEncoderMaxLineLength(length);
	}

	public MinaTextLineCodecFactory(String charset, LineDelimiter encodingDelimiter, LineDelimiter decodingDelimiter) {
		super(Charset.forName(charset), encodingDelimiter, decodingDelimiter);
		this.setDecoderMaxLineLength(length);
		this.setEncoderMaxLineLength(length);
	}

	public MinaTextLineCodecFactory(String charset, String encodingDelimiter, String decodingDelimiter) {
		super(Charset.forName(charset), encodingDelimiter, decodingDelimiter);
		this.decodingDelimiter = decodingDelimiter;
		this.encodingDelimiter = encodingDelimiter;
		this.charset = charset;
		this.setDecoderMaxLineLength(length);
		this.setEncoderMaxLineLength(length);
	}

	public MinaTextLineCodecFactory(String charset) {
		super(Charset.forName(charset));
		this.setDecoderMaxLineLength(length);
		this.setEncoderMaxLineLength(length);
	}

	public MinaTextLineCodecFactory(int length) {
		super();
		this.length = length;
		this.setDecoderMaxLineLength(length);
		this.setEncoderMaxLineLength(length);
	}

	public MinaTextLineCodecFactory(int length, String charset, LineDelimiter encodingDelimiter, LineDelimiter decodingDelimiter) {
		super(Charset.forName(charset), encodingDelimiter, decodingDelimiter);
		this.length = length;
		this.setDecoderMaxLineLength(length);
		this.setEncoderMaxLineLength(length);
	}

	public MinaTextLineCodecFactory(int length, String charset, String encodingDelimiter, String decodingDelimiter) {
		super(Charset.forName(charset), encodingDelimiter, decodingDelimiter);
		this.length = length;
		this.decodingDelimiter = decodingDelimiter;
		this.encodingDelimiter = encodingDelimiter;
		this.charset = charset;
		this.setDecoderMaxLineLength(length);
		this.setEncoderMaxLineLength(length);
	}

	public MinaTextLineCodecFactory(int length, String charset) {
		super(Charset.forName(charset));
		this.length = length;
		this.setDecoderMaxLineLength(length);
		this.setEncoderMaxLineLength(length);
	}

}
