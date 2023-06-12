package com.Service;

import javax.swing.JOptionPane;

import com.Entity.Student;

public class CollCall {
    public static Student collCall() {
        boolean continueRollCall = true;
        while (continueRollCall) {
            String input = JOptionPane.showInputDialog("请输入最多答不上来就换回答正确的人的人数：");
            int n = Integer.parseInt(input);
            RandomRollCall rrc = new RandomRollCall(n);
            rrc.rollCall();
            int numUnanswered = rrc.getUnansweredCount();
            if (numUnanswered > n) {
                continueRollCall = false;
                JOptionPane.showMessageDialog(null, "已经达到" + n + "个人未回答问题，下面由老师为大家解答这个问题。\n老师讲解中……", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int result = JOptionPane.showConfirmDialog(null, "是否继续进行新的一轮点名？", "提示", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.NO_OPTION) {
                    continueRollCall = false;
                }
            }
        }
		return null;
    }
}
