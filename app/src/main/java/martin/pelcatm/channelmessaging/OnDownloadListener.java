package martin.pelcatm.channelmessaging;


public interface OnDownloadListener {
    public void onDownloadComplete(String downloadedContent);
    public void onDownloadError(String error);



}
