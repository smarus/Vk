package ownvk.ruslan.android.myownvk.model.view.counter;

import ownvk.ruslan.android.myownvk.R;

public class CounterViewModel {

	protected int mCount;
	protected int mIconColor = R.color.colorIconDisable;
	protected int mTextColor = R.color.colorIconDisable;

	public CounterViewModel(int count) {
		this.mCount = count;
		if (mCount > 0) {
			setDefaultColor();
		} else {
			setDisabledColor();
		}
	}

	private void setDisabledColor() {
		mIconColor = R.color.colorIconDisable;
		mTextColor = R.color.colorIconDisable;
	}

	private void setDefaultColor() {
		mIconColor = R.color.colorIcon;
		mTextColor = R.color.colorIcon;
	}

	protected void setAccentColor() {
		mIconColor = R.color.colorAccent;
		mTextColor = R.color.colorAccent;
	}

	public int getCount() {
		return mCount;
	}

	public int getIconColor() {
		return mIconColor;
	}

	public int getTextColor() {
		return mTextColor;
	}
}

