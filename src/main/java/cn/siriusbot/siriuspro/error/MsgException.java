package cn.siriusbot.siriuspro.error;

import cn.siriusbot.siriuspro.web.R.R;

public class MsgException extends RuntimeException{
    R r = new R();

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MsgException(int code, String message) {
        super(message);
        this.r.setCode(code);
        this.r.setMsg(message);
    }

    public R getR() {
        return r;
    }
}
