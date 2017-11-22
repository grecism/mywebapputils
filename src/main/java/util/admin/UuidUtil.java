package util.admin;

import java.util.UUID;

/**
 * 
 *<p>Title	: UuidUtil</p>
 * @Description	: UuidUtil
 * @author	: admin
 * @date	: 2017年11月21日下午8:37:03
 */
public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	/**
     * 获得4个长度的十六进制的UUID
     * @return UUID
     */
    public static String get4UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[1];
        }
    /**
     * 获得8个长度的十六进制的UUID
     * @return UUID
     */
    public static String get8UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0];
    }
    /**
     * 获得12个长度的十六进制的UUID
     * @return UUID
     */
    public static String get12UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1];
    }
    /**
     * 获得16个长度的十六进制的UUID
     * @return UUID
     */
    public static String get16UUID(){

        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2];
    }
    /**
     * 获得20个长度的十六进制的UUID
     * @return UUID
     */
    public static String get20UUID(){

        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2]+idd[3];
    }
    /**
     * 获得24个长度的十六进制的UUID
     * @return UUID
     */
    public static String get24UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[4];
    }
	
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

