package com.runcom.jiazhangbang.Chinese;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.runcom.jiazhangbang.R;
import com.runcom.jiazhangbang.listenText.ListenText;

public class Chinese extends Activity
{
	Intent intent = new Intent();
	int selected;

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chinese);
		selected = getIntent().getIntExtra("selected" ,0);

	}

	public void ListenText(View v )
	{
		Toast.makeText(getApplicationContext() ,"Ìý¿ÎÎÄ" ,Toast.LENGTH_SHORT).show();
		intent.putExtra("selected" ,selected);
		intent.setClass(getApplicationContext() ,ListenText.class);

		startActivity(intent);
	}

	public void ListenAndWrite(View v )
	{
		Toast.makeText(getApplicationContext() ,"ÌýÐ´" ,Toast.LENGTH_SHORT).show();

	}

	public void ReciteText(View v )
	{
		Toast.makeText(getApplicationContext() ,"±³¿ÎÎÄ" ,Toast.LENGTH_SHORT).show();

	}

	public void Repeat(View v )
	{
		Toast.makeText(getApplicationContext() ,"¸ú¶Á" ,Toast.LENGTH_SHORT).show();

	}

	public void FindNewWords(View v )
	{

		Toast.makeText(getApplicationContext() ,"²éÉú´Ê" ,Toast.LENGTH_SHORT).show();
	}

	public void ComingSoon(View v )
	{
		Toast.makeText(getApplicationContext() ,"Coming soon !" ,Toast.LENGTH_SHORT).show();
	}

}
