package fr.bmartel.protocol.wlan.frame;


/**
 * Define default implementation for all wlan 802.11 data frame including
 * qos/data/NULL<br/>
 * <ul>
 * <li></li>
 * <li>duration id : 2 Bytes</li>
 * <li>Addr1 : 6 Bytes</li>
 * <li>Addr2 : 6 Bytes</li>
 * <li>Addr3 : 6 Bytes</li>
 * <li>Sequence control : 2 Bytes</li>
 * <li>Addr4 : 6 Bytes (only if TODS==1 && FROMDS==1)</li>
 * </ul>
 * 
 * @author Bertrand Martel
 * 
 */
public abstract class WlanDataAbstr implements IWlanDataFrame, IWlanFrame {

	/**
	 * duration id value
	 */
	private byte[] durationId = null;

	/**
	 * destination address
	 */
	private byte[] destinationAddr = null;

	/**
	 * source address
	 */
	private byte[] sourceAddr = null;

	/**
	 * BSSID value
	 */
	private byte[] bssid = null;

	/**
	 * receiver mac address
	 */
	private byte[] receiverAddr = null;

	/**
	 * transmitter MAC address
	 */
	private byte[] transmitterAddr = null;

	/**
	 * sequence control byte array value
	 */
	private byte[] sequenceControl = null;

	/**
	 * frame body content
	 */
	private byte[] frameBody = null;

	/**
	 * Default decoder constructor for 802.11 wlan data frames
	 * 
	 * @param frame
	 */
	public WlanDataAbstr(byte[] frame, boolean toDS, boolean fromDS) {
		if (frame.length >= 22) {
			durationId = new byte[] { frame[1], frame[0] };
			if (!toDS && !fromDS) {
				destinationAddr = new byte[] { frame[2], frame[3], frame[4],
						frame[5], frame[6], frame[7] };
				sourceAddr = new byte[] { frame[8], frame[9], frame[10],
						frame[11], frame[12], frame[13] };
				bssid = new byte[] { frame[14], frame[15], frame[16],
						frame[17], frame[18], frame[19] };
				sequenceControl = new byte[] { frame[20], frame[21] };
				if (frame.length == 22) {
					frameBody = new byte[] {};
				} else {
					frameBody = new byte[frame.length - 22];
					for (int i = 22; i < frame.length; i++) {
						frameBody[i - 22] = frame[i];
					}
				}
			} else if (!toDS && fromDS) {
				destinationAddr = new byte[] { frame[2], frame[3], frame[4],
						frame[5], frame[6], frame[7] };
				bssid = new byte[] { frame[8], frame[9], frame[10], frame[11],
						frame[12], frame[13] };
				sourceAddr = new byte[] { frame[14], frame[15], frame[16],
						frame[17], frame[18], frame[19] };
				sequenceControl = new byte[] { frame[20], frame[21] };
				if (frame.length == 22) {
					frameBody = new byte[] {};
				} else {
					frameBody = new byte[frame.length - 22];
					for (int i = 22; i < frame.length; i++) {
						frameBody[i - 22] = frame[i];
					}
				}
			} else if (toDS && !fromDS) {
				bssid = new byte[] { frame[2], frame[3], frame[4], frame[5],
						frame[6], frame[7] };
				sourceAddr = new byte[] { frame[8], frame[9], frame[10],
						frame[11], frame[12], frame[13] };
				destinationAddr = new byte[] { frame[14], frame[15], frame[16],
						frame[17], frame[18], frame[19] };
				sequenceControl = new byte[] { frame[20], frame[21] };
				if (frame.length == 22) {
					frameBody = new byte[] {};
				} else {
					frameBody = new byte[frame.length - 22];
					for (int i = 22; i < frame.length; i++) {
						frameBody[i - 22] = frame[i];
					}
				}
			} else {
				receiverAddr = new byte[] { frame[2], frame[3], frame[4],
						frame[5], frame[6], frame[7] };
				transmitterAddr = new byte[] { frame[8], frame[9], frame[10],
						frame[11], frame[12], frame[13] };
				destinationAddr = new byte[] { frame[14], frame[15], frame[16],
						frame[17], frame[18], frame[19] };
				sequenceControl = new byte[] { frame[20], frame[21] };

				sourceAddr = new byte[] { frame[22], frame[23], frame[24],
						frame[25], frame[26], frame[27] };

				if (frame.length == 28) {
					frameBody = new byte[] {};
				} else {
					frameBody = new byte[frame.length - 28];
					for (int i = 28; i < frame.length; i++) {
						frameBody[i - 28] = frame[i];
					}
				}
			}

		} else {
			System.err.println("error treating Data frame");
		}
	}

	@Override
	public byte[] getDurationId() {
		return durationId;
	}

	@Override
	public byte[] getDestinationAddr() {
		return destinationAddr;
	}

	@Override
	public byte[] getSourceAddr() {
		return sourceAddr;
	}

	@Override
	public byte[] getBSSID() {
		return bssid;
	}

	@Override
	public byte[] getSequenceControl() {
		return sequenceControl;
	}

	@Override
	public byte[] getFrameBody() {
		return frameBody;
	}

	public byte[] transmitterAddr() {
		return transmitterAddr;
	}

	public byte[] receiverAddr() {
		return receiverAddr;
	}

}
