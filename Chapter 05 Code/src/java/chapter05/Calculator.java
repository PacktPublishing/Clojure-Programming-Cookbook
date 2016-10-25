package chapter05;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {
    	public static void main( String[] args )	{
		new Calculator();
	}
	private static final long serialVersionUID = 1L;

	JPanel contentPane = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JTextField result = new JTextField(""); //計算結果を表示するテキストフィールド
	double stackedValue = 0.0; //演算子ボタンを押す前にテキストフィールドにあった値
	boolean isStacked = false; //stackedValueに数値を入力したかどうか
	boolean afterCalc = false; //演算子ボタンを押した後かどうか
	String currentOp = ""; //押された演算子ボタンの名前

	//フレームのビルド
	public Calculator() {
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(250, 300));
		this.setTitle("電子式卓上計算機");
		this.setContentPane(contentPane);

		contentPane.add(result, BorderLayout.NORTH); //テキストフィールドを配置

		JPanel keyPanel = new JPanel(); //ボタンを配置するパネルを用意
		keyPanel.setLayout(new GridLayout(4, 4)); //4行4列のGridLayoutにする
		contentPane.add(keyPanel, BorderLayout.CENTER);

		keyPanel.add(new NumberButton("7"), 0); //ボタンをレイアウトにはめこんでいく
		keyPanel.add(new NumberButton("8"), 1);
		keyPanel.add(new NumberButton("9"), 2);
		keyPanel.add(new CalcButton("÷"), 3);
		keyPanel.add(new NumberButton("4"), 4);
		keyPanel.add(new NumberButton("5"), 5);
		keyPanel.add(new NumberButton("6"), 6);
		keyPanel.add(new CalcButton("×"), 7);
		keyPanel.add(new NumberButton("1"), 8);
		keyPanel.add(new NumberButton("2"), 9);
		keyPanel.add(new NumberButton("3"), 10);
		keyPanel.add(new CalcButton("－"), 11);
		keyPanel.add(new NumberButton("0"), 12);
		keyPanel.add(new NumberButton("."), 13);
		keyPanel.add(new CalcButton("＋"), 14);
		keyPanel.add(new CalcButton("＝"), 15);

		contentPane.add(new ClearButton(), BorderLayout.SOUTH);//Cボタンを配置する
		this.setVisible(true);
	}

	/* テキストフィールドに引数の文字列をつなげる */
	public void appendResult(String c) {
		if (!afterCalc) //演算子ボタンを押した直後でないなら
			result.setText(result.getText() + c); //押したボタンの名前をつなげる
		else {
			result.setText(c); //押したボタンの文字列だけを設定する（いったんクリアしたかに見える）
			afterCalc = false;
		}
	}

	/* 数字を入力するボタンの定義 */
	public class NumberButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public NumberButton(String keyTop) {
			super(keyTop); //JButtonクラスのコンストラクタを呼び出す
			this.addActionListener(this); //このボタンにアクションイベントのリスナを設定
		}

		public void actionPerformed(ActionEvent evt) {
			String keyNumber = this.getText(); //ボタンの名前を取り出す
			appendResult(keyNumber); //ボタンの名前をテキストフィールドにつなげる
		}
	}

	/* 演算子ボタンを定義 */
	public class CalcButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public CalcButton(String op) {
			super(op);
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if (isStacked) { //以前に演算子ボタンが押されたのなら計算結果を出す
				double resultValue = (Double.valueOf(result.getText()))
						.doubleValue();
				if (currentOp.equals("＋")) //演算子に応じて計算する
					stackedValue += resultValue;
				else if (currentOp.equals("－"))
					stackedValue -= resultValue;
				else if (currentOp.equals("×"))
					stackedValue *= resultValue;
				else if (currentOp.equals("÷"))
					stackedValue /= resultValue;
				result.setText(String.valueOf(stackedValue)); //計算結果をテキストフィールドに設定
			}
			currentOp = this.getText(); //ボタン名から押されたボタンの演算子を取り出す
			stackedValue = (Double.valueOf(result.getText())).doubleValue();
			afterCalc = true;
			if (currentOp.equals("＝"))
				isStacked = false;
			else
				isStacked = true;
		}
	}

	/* クリアボタンの定義 */
	public class ClearButton extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;

		public ClearButton() {
			super("C");
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			stackedValue = 0.0;
			afterCalc = false;
			isStacked = false;
			result.setText("");
		}
	}
}
