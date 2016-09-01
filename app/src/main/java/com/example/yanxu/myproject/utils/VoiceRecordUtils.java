package com.example.yanxu.myproject.utils;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class VoiceRecordUtils {
	
	private int frequency = 11025;
	private int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;  // MONO为单声道
	private int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
	private String voiceName ;               //录音文件名称
	private final int audioSource = MediaRecorder.AudioSource.MIC;              //Microphone(麦克风) audio source
	private File file;
	
	private int bufferSize = 0;
	private AudioRecord audioRecord;
	private boolean isRecording = false;       //用来判断是否在录音
	public String getSaveAbsolutePath(){
		return Environment.getExternalStorageDirectory().getAbsolutePath()+"/dhyt/voice/"+voiceName;
	}
	public void delete(){
		file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/dhyt/voice/"+voiceName);
		if(file.exists()){
			file.delete();
		}
	}
	/**
	 * 初始化录音对象
	 */
	public void initvoiceRecord(String voiceNameDefine){
		voiceName=voiceNameDefine;
		file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/dhyt/voice/"+voiceNameDefine);
		//删除之前的记录，并重新创建
		if(file.exists()){
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		bufferSize  = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
		audioRecord = new AudioRecord(audioSource, frequency, channelConfiguration, audioEncoding, bufferSize);
		
		
	
	}
	
	
	
	/**
	 * 1 开始录音
	 * @author Administrator
	 *
	 */
	public void startRecord(){
		new voiceRecordTask().execute();
		
		
	}
	
	
	/**
	 * 2 暂停录音
	 */
	public void pauseRecord(){
		isRecording = false;
	}
	
	/**
	 * 3 继续录音
	 */
	public void continueRecord(){
		new voiceRecordTask().execute();
	}
	
	/**
	 * 4 停止录音
	 */
	public void endRecord(){
		
		isRecording = false;
		
	}
	
	/**
	 * 5 播放录音
	 */
	public void playRecord(){
		play();
	}
	
	
	
	/**
	 * 后台运行录音程序
	 * @author Administrator
	 *
	 */
	public class voiceRecordTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			if (null == audioRecord) {
				initvoiceRecord(voiceName);
			}
			
			//使用随机访问类,用于暂停后继续录音时，向源文件追加内容
			try {
				RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
				//缓冲数组  why bufferSize/4 ???
				//byte[] byteArray = new byte[bufferSize/4];
				short[] shortArray = new short[bufferSize];
				
				//开始录制音频
				audioRecord.startRecording();
				
				//判断是否正在录制
				isRecording = true;
				while (true == isRecording) {
					
					int bufferReadResult = audioRecord.read(shortArray, 0, bufferSize);
					//向原文件中追加内容
					randomAccessFile.seek(randomAccessFile.length());
					for (int i = 0; i < bufferReadResult; i++) {
						randomAccessFile.writeShort(shortArray[i]);
					}
					
					/*
					audioRecord.read(byteArray, 0, byteArray.length);
					
					//向原文件中追加内容
					randomAccessFile.seek(randomAccessFile.length());
					randomAccessFile.write(byteArray, 0, byteArray.length);
					*/
				}
				
				//停止录音
				audioRecord.stop();
				//关闭节点流
				randomAccessFile.close();
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			return null;
		}
		
	}


	
	
	
	
	
	
	/**
	 * 播放录音
	 */
	public void play() {
		// Get the file we want to playback
		/*
		 * File file = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/reverseme.pcm");
		*/

		//File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/AudioRecorder/"+voiceNameDefine);
		
		// Get the length of the audio stored in the file(16 bit so 2 bytes per
		// short)
		// and create a short array to store the recorded audio
		int voiceLength = (int) (file.length() ); // ???
		short[] music = new short[voiceLength];

		try {
			// create a DataInputStream to read the audio data back from the
			// saved file
			//字节流
			InputStream inputStream = new FileInputStream(file);
			//缓冲流
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			//处理流，该处理流中提供了更多的read方法，而InputStream中只有read();
			DataInputStream dataInputStream = new DataInputStream(bis);

			// read the file into the music array
			int i = 0;
			while (dataInputStream.available() > 0) {
				music[i] = dataInputStream.readShort();
				i++;
			}

			// 关闭输入流
			dataInputStream.close();

			// Create a new AudioTrack object using the same parameters as the
			// AudioRecord
			// object used to create the file
			AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
												   11025,
					                               AudioFormat.CHANNEL_CONFIGURATION_MONO	,
					                               AudioFormat.ENCODING_PCM_16BIT,
					                               voiceLength*2, 
					                               AudioTrack.MODE_STREAM);
			//开始播放
			audioTrack.play();
			
			//write the music buffer to the AudioTrack object
			audioTrack.write(music, 0, voiceLength);
			audioTrack.stop();
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("AudioTrack","Playback Failed");
			e.printStackTrace();
		}

	}
	

	public String getVoiceName() {
		return voiceName;
	}



	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}
	
	

}