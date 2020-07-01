package com.example.mvpdemo.model;

import com.example.mvpdemo.R;
import com.example.mvpdemo.bean.Emoji;

import java.util.ArrayList;
import java.util.List;

public class EmojiModel implements IEmojiModel{
    @Override
    public void loadEmojiData(OnLoadListener onLoadListener) {
        onLoadListener.onComplete(getData());
    }
    private List<Emoji> getData(){
        String[] strs={"难过","骚气","开心","欠揍","拜拜"};
        String[] stars={"*","**","*****","***","*"};
        int[] ints={R.drawable.icon_002_cover,R.drawable.icon_007_cover,R.drawable.icon_010_cover,
                R.drawable.icon_012_cover,R.drawable.icon_013_cover};
        List<Emoji> list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Emoji emoji=new Emoji();
            emoji.setImageId(ints[i]);
            emoji.setState(strs[i]);
            emoji.setStar(stars[i]);
            list.add(emoji);
        }
        return list;
    }
}
