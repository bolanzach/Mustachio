/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Zach Bolan
 */
public class DBAccess {

	private static DBAccess instance = null;

	/**
	 * Singleton Pattern. We initialize the database only once.
	 */
	protected DBAccess() {
		Connection con = getConnection();
		initializeDB(con);
		try {
			con.close();
		} catch (SQLException ex) {
		}
	}

	/**
	 * Return the instance of the DBAccess class. This class is a singleton
	 * because there should be only one access point to the database.
	 *
	 * @return
	 */
	public synchronized static DBAccess getInstance() {
		if (instance == null) {
			instance = new DBAccess();
		}
		return instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Zach\\GlassFish_Server\\bin\\mustachio_z.db");
		} catch (Exception e) {
                    System.out.println("asdfasdfasdf" + e);
		}
                System.out.println(connection);
		return connection;
	}

	private void initializeDB(Connection connection) {

		try {
			Statement statement;
			ResultSet rs;

			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			String sql = "";

			// Member Table
			sql = "CREATE TABLE IF NOT EXISTS member("
					+ "id INTEGER PRIMARY KEY NOT NULL, "
					+ "username TEXT, "
					+ "password TEXT, "
					+ "profile_picture_id INTEGER DEFAULT 1, "
					+ "bio TEXT, "
					+ "is_admin INTEGER DEFAULT 0, "
					+ "is_flagged INTEGER DEFAULT 1"
					+ ");";
			statement.executeUpdate(sql);

			// Vote Table
			sql = "CREATE TABLE IF NOT EXISTS vote("
					+ "id INTEGER PRIMARY KEY NOT NULL, "
					+ "vote_owner INTEGER, "
					+ "vote_for INTEGER"
					+ ");";
			statement.executeUpdate(sql);

			// Image Table
			sql = "CREATE TABLE IF NOT EXISTS image("
					+ "id INTEGER PRIMARY KEY NOT NULL, "
					+ "data BLOB, "
					+ "alt TEXT, "
					+ "member_id INTEGER, "
					+ "mime TEXT"
					+ ");";
			statement.executeUpdate(sql);

			// Here we do a checks to see if the tables contain any data.
			// This is to prevent filling the database with redundant data
			// everytime the application is run.
			sql = "SELECT * FROM member";
			rs = statement.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				System.out.println("Populating Member Data...");
				sql = "INSERT INTO member (username, password, bio, profile_picture_id, is_admin, is_flagged)"
						+ "VALUES ('Zach', 'pass', 'I am Zach', '1', '1', '0');";
				statement.executeUpdate(sql);
				sql = "INSERT INTO member (username, password, bio, profile_picture_id, is_admin, is_flagged)"
						+ "VALUES ('Peter', 'pass', 'I am Peter', '1', '1', '0');";
				statement.executeUpdate(sql);
				sql = "INSERT INTO member (username, password, bio, profile_picture_id, is_admin, is_flagged)"
						+ "VALUES ('Mike', 'pass123', 'I am Mike', '1', '0', '0');";
				statement.executeUpdate(sql);
				sql = "INSERT INTO member (username, password, bio, profile_picture_id, is_admin, is_flagged)"
						+ "VALUES ('Morgan', 'password', 'I am Morgan', '1', '0', '0');";
				statement.executeUpdate(sql);
				sql = "INSERT INTO member (username, password, bio, profile_picture_id, is_admin, is_flagged)"
						+ "VALUES ('Luke', 'foobar', 'I am Luke', '1', '0', '0');";
				statement.executeUpdate(sql);
			}

			sql = "SELECT * FROM vote";
			rs = statement.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				System.out.println("Populating Vote Data...");
				sql = "INSERT INTO vote (vote_owner, vote_for)"
						+ "VALUES ('1', '0');";
				statement.executeUpdate(sql);
				sql = "INSERT INTO vote (vote_owner, vote_for)"
						+ "VALUES ('2', '1');";
				statement.executeUpdate(sql);
				sql = "INSERT INTO vote (vote_owner, vote_for)"
						+ "VALUES ('3', '1');";
				statement.executeUpdate(sql);
				sql = "INSERT INTO vote (vote_owner, vote_for)"
						+ "VALUES ('4', '1');";
				statement.executeUpdate(sql);
				sql = "INSERT INTO vote (vote_owner, vote_for)"
						+ "VALUES ('5', '0');";
				statement.executeUpdate(sql);
			}

			sql = "SELECT * FROM image";
			rs = statement.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				System.out.println("Populating Image Data...");
				sql = "INSERT INTO image (data, alt, member_id, mime)"
						+ "VALUES ('ÿØÿà JFIF  H H  ÿí bPhotoshop 3.0 8BIM     E    t 9© hopper1982 - http://www.redbubble.com/people/hopper1982 ÿâXICC_PROFILE   HLino  mntrRGB XYZ Î  	  1  acspMSFT    IEC sRGB              öÖ     Ó-HP                                                 cprt  P   3desc  „   lwtpt  ð   bkpt     rXYZ     gXYZ  ,   bXYZ  @   dmnd  T   pdmdd  Ä   ˆvued  L   †view  Ô   $lumi  ø   meas     $tech  0   rTRC  <  gTRC  <  bTRC  <  text    Copyright (c) 1998 Hewlett-Packard Company  desc       sRGB IEC61966-2.1           sRGB IEC61966-2.1                                                  XYZ       óQ    ÌXYZ                 XYZ       o¢  8õ  XYZ       b™  ·…  ÚXYZ       $   „  ¶Ïdesc       IEC http://www.iec.ch           IEC http://www.iec.ch                                              desc       .IEC 61966-2.1 Default RGB colour space - sRGB           .IEC 61966-2.1 Default RGB colour space - sRGB                      desc       ,Reference Viewing Condition in IEC61966-2.1           ,Reference Viewing Condition in IEC61966-2.1                          view     ¤þ _. Ï íÌ  \\ž   XYZ      L	V P   Wçmeas                            sig     CRT curv           \n"
						+ "     # ( - 2 7 ; @ E J O T Y ^ c h m r w |  † ‹  • š Ÿ ¤ © ® ² · ¼ Á Æ Ë Ð Õ Û à å ë ð ö û\n"
						+ "%+28>ELRY`gnu|ƒ‹’š¡©±¹ÁÉÑÙáéòú&/8AKT]gqz„Ž˜¢¬¶ÁËÕàëõ !-8COZfr~Š–¢®ºÇÓàìù -;HUcq~Œš¨¶ÄÓáðþ\n"
						+ "+:IXgw†–¦µÅÕåö'7HYj{Œ¯ÀÑãõ+=Oat†™¬¿Òåø2FZn‚–ª¾Òçû		%	:	O	d	y		¤	º	Ï	å	û\n"
						+ "\n"
						+ "'\n"
						+ "=\n"
						+ "T\n"
						+ "j\n"
						+ "\n"
						+ "˜\n"
						+ "®\n"
						+ "Å\n"
						+ "Ü\n"
						+ "ó\"9Qi€˜°Èáù*C\\uŽ§ÀÙó\n"
						+ "\n"
						+ "\n"
						+ "&\n"
						+ "@\n"
						+ "Z\n"
						+ "t\n"
						+ "Ž\n"
						+ "©\n"
						+ "Ã\n"
						+ "Þ\n"
						+ "ø.Id›¶Òî	%A^z–³Ïì	&Ca~›¹×õ1OmŒªÉè&Ed„£Ãã#Ccƒ¤Åå'Ij‹­Îð4Vx›½à&Il²ÖúAe‰®Ò÷@eŠ¯Õú Ek‘·Ý*QwžÅì;cŠ²Ú*R{£ÌõGp™Ãì@j”¾é>i”¿ê  A l ˜ Ä ð!!H!u!¡!Î!û\"'\"U\"‚\"¯\"Ý#\n"
						+ "#8#f#”#Â#ð$$M$|$«$Ú%	%8%h%—%Ç%÷&'&W&‡&·&è''I'z'«'Ü(\n"
						+ "(?(q(¢(Ô))8)k))Ð**5*h*›*Ï++6+i++Ñ,,9,n,¢,×--A-v-«-á..L.‚.·.î/$/Z/‘/Ç/þ050l0¤0Û11J1‚1º1ò2*2c2›2Ô3\n"
						+ "3F33¸3ñ4+4e4ž4Ø55M5‡5Â5ý676r6®6é7$7`7œ7×88P8Œ8È99B99¼9ù:6:t:²:ï;-;k;ª;è<'<e<¤<ã=\"=a=¡=à> >`> >à?!?a?¢?â@#@d@¦@çA)AjA¬AîB0BrBµB÷C:C}CÀDDGDŠDÎEEUEšEÞF\"FgF«FðG5G{GÀHHKH‘H×IIcI©IðJ7J}JÄKKSKšKâL*LrLºMMJM“MÜN%NnN·O OIO“OÝP'PqP»QQPQ›QæR1R|RÇSS_SªSöTBTTÛU(UuUÂVV\\V©V÷WDW’WàX/X}XËYYiY¸ZZVZ¦Zõ[E[•[å\\5\\†\\Ö]']x]É^^l^½__a_³``W`ª`üaOa¢aõbIbœbðcCc—cëd@d”dée=e’eçf=f’fèg=g“géh?h–hìiCišiñjHjŸj÷kOk§kÿlWl¯mm`m¹nnknÄooxoÑp+p†pàq:q•qðrKr¦ss]s¸ttptÌu(u…uáv>v›vøwVw³xxnxÌy*y‰yçzFz¥{{c{Â|!||á}A}¡~~b~Â#„å€G€¨\n"
						+ "kÍ‚0‚’‚ôƒWƒº„„€„ã…G…«††r†×‡;‡ŸˆˆiˆÎ‰3‰™‰þŠdŠÊ‹0‹–‹üŒcŒÊ1˜ÿŽfŽÎ6žnÖ‘?‘¨’’z’ã“M“¶” ”Š”ô•_•É–4–Ÿ—\n"
						+ "—u—à˜L˜¸™$™™üšhšÕ›B›¯œœ‰œ÷dÒž@ž®ŸŸ‹Ÿú i Ø¡G¡¶¢&¢–££v£æ¤V¤Ç¥8¥©¦¦‹¦ý§n§à¨R¨Ä©7©©ªª««u«é¬\\¬Ð­D­¸®-®¡¯¯‹° °u°ê±`±Ö²K²Â³8³®´%´œµµŠ¶¶y¶ð·h·à¸Y¸Ñ¹J¹Âº;ºµ».»§¼!¼›½½¾\n"
						+ "¾„¾ÿ¿z¿õÀpÀìÁgÁãÂ_ÂÛÃXÃÔÄQÄÎÅKÅÈÆFÆÃÇAÇ¿È=È¼É:É¹Ê8Ê·Ë6Ë¶Ì5ÌµÍ5ÍµÎ6Î¶Ï7Ï¸Ð9ÐºÑ<Ñ¾Ò?ÒÁÓDÓÆÔIÔËÕNÕÑÖUÖØ×\\×àØdØèÙlÙñÚvÚûÛ€ÜÜŠÝÝ–ÞÞ¢ß)ß¯à6à½áDáÌâSâÛãcãëäsäüå„æ\n"
						+ "æ–çç©è2è¼éFéÐê[êåëpëûì†ííœî(î´ï@ïÌðXðåñrñÿòŒóó§ô4ôÂõPõÞömöû÷Šøø¨ù8ùÇúWúçûwüü˜ý)ýºþKþÜÿmÿÿÿí bPhotoshop 3.0 8BIM     E    t 9© hopper1982 - http://www.redbubble.com/people/hopper1982 ÿÛ C 	!\"$\"$ÿÛ CÿÀ && ÿÄ             	ÿÄ P 	  !1AQ\"aq2B‘#Rr‚’¡3b¢±Â	4S”²ÁÒCTVcƒ“ÃÑÓ%&5FstÿÄ               ÿÄ         1!AQÿÚ   ? ¹`                                                                               ‚öòÒÊ‹­{uBÚ’ñjŠ_V§˜â·\n"
						+ "q2q¿×Z~œ×Œc}	É}\"Û:¾Ðœ·ßÿ ¼¨VkýMµiÿ \n"
						+ "‚åu•»MpŠ›Ùg/j~¦:·üb¯Ê]§8E9ló7ðøËWÿ `eg[ö‹àýe¿ò³»ý{ëü Êæÿ 8Nÿ Û_ìwüc®k^<ð®îN6š–¥Ì—Š£º›_»Mƒ\\:Kw–È%¾Û¼5æÛÿ åu÷jnXÝJÞêÃTQ¨›ÙTÆr9-öæJROo 1Ãþv<.ÿ «ê?öÿ Î0Ê¿êúý‚?óŒ2ŸçcÂïú¾¤ÿ `üã­§Ç®dìÕÄêg1ó”šT.°·=ãKï{’ÙüÁŒÊ¼oá­*N­lÝå*kÆsÄ]Æ+êélaÿ œ'ÿ í/ö;þ1†WÌûCp~1rþXS{y+;ÿ ÜV\n"
						+ "~Òü!¥áŸº«ú˜úßñˆ2¸cÚw„mÿ ù|‚ùãªÿ ì0ÊÎ¶íÂ\n"
						+ "í'ªeKÿ éc]€]Æ?Ü&¾”cC^ábåáßVt¿ßHÛ±Ofxœö/!¿Uì×têïû­„v€                                     ­Ô™ü.›ÅUÊçò–¸Û*_jµÅEü–þ/àº]¸…ÚÛ	c:–š#[-R/eyzÝ\n"
						+ "â ½ù/Ÿ)qqêÞ?ñ[QNJ¦¨«Œ¡&ö¡Œ¦­ÒOË™o7ûÃ¾Nþÿ )UÕÉß]_Tow;šÒªÿ 6Œ’Kd¶E    '¿ÆÜFç}ue^/xÕ·­*s_'˜\n"
						+ "»IñK\\Ó¥™¼z›ÍùÊ7¯óétû¼wÛô¹‘11g2XnvŒáÅ–SjÊš›îni¨ÂîÊªkž›Ý5óOtÓOÑ‘=N—dŽÇúLÞ¥Ÿþ=ÿ ¤]5ÉS²Wd½Ü¶¥‹ÿ ý4_þÓ]Æ€ìÕ 4Ž¨¡Ÿ\\–^µ¶ò·£•9Ò§?)òÆv¼·ßoê?ã‡j*Ö9;­?ÃšVõ%o7J¶^¼9âæžÍQ‡ƒK¯½-ÓòOÄ¸²+N¨ÖÚ¿TUï5¦Êä½!Zæ]ÚùAmôAZù@    CÜªªÃÝ¨¼$º5õoÓNâš”#kÅEïÝNáÖ¦þp©Ì¿Lšµ¶¨±•:¿e˜¢ºJâÍû=Ÿ+ÞéÊ11dxeÅÝ	Ä(*x¼cË¼¬.—uq_uý¥ñ‹h‰ð                                  q÷Œ˜^â!MÂýÜ³°SÛ§‡yQ¯³þ­ô^mMQ-­µ6»ÍK-©ò•oknûªmíJ„Fœ<\"¿‹ól­5Ò€        -7ù?2õc•Õxßu:4/b·ð’r„ŸÕ8þ©VèŒ€iœrÌÕÀpUå¨=«PÆVTÚò”£ÊŸã$óJ)$’ð]\n"
						+ "6        î…Z´+Ó¯B¬éV§%*u)ÉÆP’ði®©üP›³·i;˜][in#Ý÷´ª5N×3Qí(IôQ¯êŸéþ÷©Å¶‹RŠ”Zi­Ó^deú                                t\\AÕ3Fe5>KwoaAÔäOgR^‚øÊM/¨ië-I–ÕÚžûQç.{ûÚœó{ô‚û°¤b¶I|\n"
						+ "Û¨(         ½Ù^ßˆZ×'K‡Y,6?%ù9Ê½L)Nœ¨ªÝ.TÞüÎ?Ä•*Ç<7jGÿ íú|­*ÈDñùùµ'ý°Ð_ì•?øÁãLã–;Öü)ÎÖÖG]à•(+º6VÓiÅÔ‚\\­Å/´ãô)1QÊÐ         “[5º`]NÅ<L¸ÔzvãDf®e[!‡§ÙU©-åV×¤y[ñneò”}³bÆ                              .íÆnÚâ'S	W-['R¬a×T£N4Ôy›{=úÎ)-½BÉªÅÇ¾?ÔâvŽ·Ó–ø	â)Fò75äî»ÞõF/–?f;{Ï?\\YiT          [°¬§®µ5ï+å£Œ¥K$çW}¿°JŸ¥Ê# ßi{YÞpXQ„\\¥tª¤¿©%?ð…yÆi           m¼ Öµø{Änª¡nîckÏ\n"
						+ "Öê|½õ9ÅÆQßg·Š~ªD*Ðh~ÕvzXâ4ým^Ò9+ÊvŠ¼o•NîS’Œ[\"ÝnÖýF3‹&D                             _;epëWë»=7[Jb–Iãçr®)*ð„Ò¨©ò´¦ÒaùúŠw«ô¶¡ÒXâµ..®2úT£YQ©(Ê\\’m){­¯'øiÓ           '°vú/Qg*Ce{…½9zÆ”7Æ£ü	Y«.D ë5f1f´¶W$š¾²­o³ð÷àãÿ <´©J­\n"
						+ "“¡Y8Õ§'\n"
						+ "‰®ªIì×âi·È           'pGŠx‰¦³šZ¥–>Ó#mwZâ½Í$•(N3m%&ÛÙtIRÕð#                               PþÛÊKŽÕ[ßgŠ¶Ûñ¨XÔâ*€         “QM¿Õè÷g-3-'Á;‹«IS¹©míw)¬¬ÝG¿Ë™/¡–jB Î>Òié^4ê<|iªv÷.úÝGÃ»­ïÿ 	9/¡csˆì          èõ?Uÿ qª:Q5¥±*^*ÊŽÿ ¸ˆÃ²                               Ã·Ö2T8ƒ€Ë¨>îó*.[tæ¥Q½¿\n"
						+ "ˆ±¨­ÅP         7®hÙë¾*a°N›•œk+›æ¼\n"
						+ "mJIþ·HþÑ)^“$¢’I$º$ˆÀ  ·¦Œ•Þ®¬éo;	ûóKþŠozr)î¿ñ)ùZ         ;OØTÊç±ØºPsåÝ+x¥ã¼æ£ÿ SèSèÁmEF?$¶#°                              ö×Ò3Ô<$ü³mIÎëp®úx÷2\\•ã/ØLÓ@        \n"
						+ "¤·}‡±gç¥ô5MY“·tò™øÆt£8í*V«­5ðæoŸäãèf³j  W«pXýO¦r:{)O¼³È[Î…Uæ”—Šø§³_€ó;]éŒ–Õù-3–ƒÕ…gMËm•HxÂ¢øJ;5ó+n         	›±Ö’ž¥ã5–B¥7+,õYywcI|ùŸ7ì¥«ôF@                              Å{mo{gZÎê”+[×§*UiÍoÂKfŸÁ¦œ\\wáÍç\n"
						+ "5õÖpœ±•›¯Œ®úª”éÿ Jeý™[—Z	@       	{³\n"
						+ "kqYÆó#FKNbªF¥ì¤º\\OÆ4®þ2ôÍ¥¯@)Â©Æ8F‚QŒb¶I/‘}    kîÏYéÈêÌ\n"
						+ "·>I÷” ½ëËuÕÃã8õqõ÷—š*Ž/M        }Ð¥V½jt(RZÕ$¡Nœòœ›Ù$¼Û} ô3³G\n"
						+ "W\n"
						+ "øyNÖöü·‘’ºÉI=ùg·»I?H.Ÿ7'æe›Rˆ@                                Óø¹ÃÜt•lfÝE½K;¸E:–µvé8ú¯'º\\yõÅêNj9áµ\n"
						+ "«Ží»kºi÷7P_zþø¾«Ì­îµB€     óÁžç¸©ã‹ÅÂT,hµ+ü„£½;hŠoÊ?WÐ…¸ô7Cél.ŒÓzwikXmã)ËïNoïI¾­‘‡t     Q;Xp\"­­{­}¢l\\í§½\\®:Œwtåã*ôâ¾ëñ”W‡Šé¾Ö5/ÅZM5º{¢¨     ÉoFµÅÅ;{z5+V«5\n"
						+ "téÅÊS“ðI.­s{.pZV¥\n"
						+ "i­(BYÇk+%%bŸß—“«·îüü#6¬y                                  ê5~™ÀêÜ%l6¢ÆPÈXÕñ§V=bü¥ºÆKÕ4À©üSì¡š°­Vÿ ‡÷ðÉÙ½ä±÷“P¯MzF§ÙŸ×•üË­j¼êM?ÓW²²Ô8{ìUÄ^Ü—t%O“}%óM¢«¬   XŒnG/}M…ÖBîm(Ñ¶£*³{ü\"› °<'ì³©suhä5ÕwÇt“³¥%;º«Ñµ¼iÿ ðCRÕ½ÑÚc¤040zwFÂÆ‚÷iÓ]dßŒ¤ßYIù·ÔŒ»€       +Ï;2á5EÅÆsFV£ƒËTnumeì—}[Ùu§'×ªM|<Ë«*§ë¾k]s*Z—O^YÓM¨ÜÆå¼þ*¤wâÓøšªi­ÓÝ2€  }R„êÖP•J³{Fœ”¤þ	u`K<8ìõÄ}c*Uêã? c§³wY8¸IÇÇxÒûrú¤¾$ÔÕ·àïto\n"
						+ "¡»:Éæ¹yg’»Šuþ*œ|)¯—_VÈ–¤à€                                    1²Xû«µÉXÛ^ÛËÆ•Å(ÔƒúI4q¨;?ð“3Ï*ºBÚÎ¤Þî¥Z–ï”$£üíjw]“øcU·BïQ[&ü!y	%ûÔÛþ#MqÛöLá¬õ²Z–²ôwT¢ŸáM2éµ²`»8p‹ù¥¦§‘Ÿ­õÝZ«÷y”\n"
						+ "©/Â`-U®a£¶Ü–¶ñ¤ŸÏ•-Â;         óZ•:Ô¥Jµ8T§5´¡5º’ôi jn\n"
						+ "ð·QUlŽŒÆÆ¼üjÚÅÛÏç½7»ZEçe.Ö“t*gí7ò§|¤¿·\n"
						+ "5‹K²W#=ç•ÔÓ^žÓEé\n"
						+ "5Þa»2pQN®û\"×•ÝýFŸÍEÅ?ÀR6–Ñ:CKSŒ4öšÅc|'omÏ÷¶Ýþ!                                                                                                                                                                      Kâ4wl#_Qä”njE»{*žâ·êÇÉZ[/ˆ1Võ×k\n"
						+ "i“¯:ZOc´ßÝ©Z\n"
						+ "æá¯Žþâùr¿™q¬hö] 8½m¨ë‹‰sCZÚ”éË.UîÙú2,þ[´>H-;‰×X«»]A{cFã-BÎ*pÇJ¢è¦œ¹·~<«v‘OóëÂ_a»½ZÛèÚT*›F£”¥-öä/4×GÖ)®€ÇkŸâŸ°XVw)ª,¨c²ë{\n"
						+ "ëšj²ÛvÒŠm%æÚ[yìÐj®?ð·C!¾¤¥‘»²Œ_²ÙBU'YËm”%·$¼Vï›eæ*8â§óYXêÞ[;Zs¾žZ­Í(Ô­Ž—*tÓ‡Xí=úMî¼‹ œoh/XÜ÷ëXW¸õ§qmFpNOîØ¸¹\n"
						+ "»[)Ö¥eÄ$iEí‘Æ¦ÒøÎ‹mþë!†,ö›Îáµ&\"Ž_’¶ÈØV[Ó¯BjQƒø>¨Œ»                                         ç»Ch½“üo\n"
						+ "Úƒ-¨×¶±œyhz©Ô}9¿ª·~»‘0ÒŸyJ9\\y¢žÏÅnô    uš—P`ôÖ.¦SPelñ–TþÕkšªßÑoâþ¨f«íg¡1·¡ƒÄåó¼­§V1½7òs÷šý‘‹kUv¹¶¸Ñ•“ZzæËQU¨éÁ^J5(Ð†ßÒï¶÷è¢ÒëÕôè\\\\UlÖO#šÊÜer÷µï¯®gÏZ½y¹Noçéð]VFn){†ÉÑÉck*”5¼ŠNœ¼¤·Mn¼Sòd9ÛÌõ{ü…Ý{»»‰º•«×¨çR¤Ÿ‹”ŸVÊ8 æ­usZ…zÕêT¥n¥”›5'Í%å»êöó á(í0Zƒ3ƒÝ<^Bµ½ÚNÝ÷¥qOôgîÉ|×O-ˆ:ÇÕîQøãÂž$j~ç£“À]ÉÐœ—µXÕ“t.b¼¤¼¥·„—UüY«/‘í¦hÎÝXé½ÔgJ2®å^>îm{Ð^<Û>›ôÜ˜Î7-	ÚS†zšµ+K»ë?ySd¡’‚…6ß’«áø´L”jÓ­F¨Ô…JsŠ”'	o'àÓ^(#è    ÓxÉ¯møm¡ëj›¬eÆJ•+ŠT]\n"
						+ "3Q—¿%÷}:>xWÄÝ%ÄŒ[»Ó·êW’w6U¶…Åýhù¯ë-Óõ7@                              •ÈXâ±·,•ÕK;jn¥jÕd£Ex¶Ø×Ý¤ò:ŽW{@Ö¯Ã=éÖÈmËqv¼'8?_´þÆ¤h™´%]yÅlu\n"
						+ "´œ±˜ÚŠÿ !7áË	o|ç=—Ë™ù½#    ‡;CqËÃK_ÉXØQÉêzÐæ§jåù»h¿	ÕkªßÊ+«ø.¡dÕÖú¿Rk\\ÌòÚŸ-_!rÛäS{S¢ŸÝ§Òà¾»•§DP            I|ãF®á¥Ý:6—É`ù·­‹¸Ÿ¹·›§/rùtõD,^ÎkÍ=Ä=3O;§®\\é·É^…M•[z›u„ãäþ>\n"
						+ "uDbÌm   [â~•·ÖÚ3¥î$ ¯í¥\n"
						+ "uþŽ¢÷¡/¤”Xn[×Ôz#VÔvõîðÙÌ]yR›§'Òœ^ÒOÕ?GÑ¢¶¸Ýž»EcõK}7¬¾3PÉ(P¯ËBú^‹±Qþƒòô#6,@                            ŠõiP£:õªB•*qsœæöŒb–í¶ü+´Ï.ø‰›©…ÂÜT£¥lªµF1Ý{lÓþš~±ýäºø¾•©¹U)ð“Šš×IØ[é>b1‘Èä®Ó«V¥´«Ü^U}!´’ŒWD’éï=ú²&=ÂþPX{%–t^GÙéû[¢š¦êò®~]þï6û|Ë,  #Ñ¼Q£Ã+«^î®výº8Ú[®m½ê²_£÷ø¶—˜Y5ç¶Rþ÷+’¹Éä®ªÝÞÝTuk×«.iÔ›ñmšiŒ              éÁÎ!åøm¬íó¸éN­¬š§gÍ´nhïÕ~²ñ‹òÈY¯FôÆo©4õ†{p®,/¨F½\n"
						+ "‹Î-yú5à×“LŒ;  {GgµÞ—áÕ\\þ„§iRâÊ²©|«Pïd­¶|Ò„w[ìù[ñé»òO‰zÖë^ç)ç²˜ÛL´©*wu¬ã(BåÇ¤g(6ö–ÝOªK§Bµ¯ši´ÓÝ4öi”\\îÈül­©iSÐº¶ï¼ÌÐ§ÿ Óï*>·”âºÂOýdWŸÞKÕ=ã6,‘                           \\»oñ\n"
						+ "¦IÛhŒewN÷7;ÉAí(ZÅíË¿—<º|£$\"Å,4Ð§ì%¢¬ªÜä5æF4Ýzu%cŠŒÚÝ5ëTŠó{J1ø{Ä©SÏ8µ¥¸aŒ…\\½Y]d«Å»\\m¼—}Wo¼÷ûßï?¦ï¡ÇoÂmUq­øw‡ÕwXÅŒ©’¥*ªÙUï9#Ï%Í²ßt“ðó¿Ëd,q8ËŒžNê•¥µ7Rµj²åŒ\"¼[`CúŽ¸ìÎœÖúÓ5Øiœ6A[ã'ÊÕ[˜wi¥³}jJO¤W‡2OÁ°¸§œbâ&_‰šÆ®)ÛÒ„{›+H½ãoGvÔwó“ßy?7ðH­HÓ\n"
						+ "                °=”¸Ú´MÝ=©ª¯äíÝvíîdúØÕ›ë¿ýÜŸè·¿ƒdKÓ‹´,øý—á¶ ö{;z”-ªá.›ÙW”é©Nœ›é»mòþ«^;3Ä²qÇŒTxU›ÓÔrz~æû•ï•kºRŠ\n"
						+ "{ïio¶ëÃ Y5¾émAÖ:rŽc	{o’Æ]Á¥(õOÊP”_ƒòqa|v…Ñ¶ÚŠy,V;•â®oqî=b¨ÔßÝOÍFJQù$Vâ>(ÉÅßÞâ²v¹<uÄí¯m*Æµ½h=¥Nq{¦¾¤•pYÛkþâµ=T¸¥ËuJ/~ê¼}Ú‘ýäöø4Fh                           7ûDêyjÞ2ê,œjw–ôn]•·¢¥GÜ_‹RRÆâ?(¸ð³^_hm[Ô4éÊöXºP²¶©QªQjrŽízs5'·W±u—Yíi­(jœÛï²	ÞßÖßóT÷÷šKÁF;òÅz$€“xƒÇ|–nÿ §ôÝ|†œÑÙQ£N…œÕ;»ŠÚ[Îkì¶—H§²óÜbc ã75gî%mwUc°PŸ5e¼½Îž©/’ùôOÁ$Æ¯žÕuò7¤íh«<f/¼¯RœúMÕI7*Óõj<°KÉ'ê­”   ÚKvö@fæqY,5Ü-2¶UìëÔ£\n"
						+ "ð…h8¹ÓšRŒÖþ1i®¨ƒ	4üþE >çN¤#	Nœ£‘çƒke(ú¯UÑð  s§R¡VtçuP“[)5â“óØ€{›VIàåœV7“Êµö®GÝ÷®.\\Š^\n"
						+ "ì›Øƒ   “[5º`lº¯V\\jLž³ÈÒu28Z´÷;ï+[©)Q„¾4ýäŸŽÍzo6¼zÖ<9½ÑÙ¼•íZœ–;/m]Ó¼·©	ÆQŒäŸç öq{ìö~,bc—7ÅŠÜGáu}#®êB¦oÕÞ-È¢êÎ	©Q­·DåÒšèÞÛúƒÇ¸œáÎRâVSÆ*ú›¥c)µ‰¦”ãú5ý%ô}Ù­\"­ÍÅ[[kZ·jÑµƒ§B3“jœ[riz-Û{|XE -W`=MR7º‹GÖ¨Ý9Âh¹xImN¢Kâ»·ô%J¶Äd                          uº¯\"°ú_-—m%eeZãwáîAËþ ydêTªÝZ¯z“|Ó~²}[üM6ü         	³÷¨ñ+ˆÖøË¹ZÙR¥+»§sJ¥8J)Ó^Ž\\Ûnü:ù’–ãÐA£´¦¡°·°ÎiìfFÚÚ*4!so÷I-’‹kuô#\n"
						+ "ŠÜÑ:çeŽ¶µ¥§n1êJÎãoªq—YFPÙ)Å¾¾O~»øî]i|>ì£¤ð™Hd5>^¾£î¥ÍNÕÐT-Û^iJN-ÒõLºk~ãG4¿1vT.¥S}‡wewiþnŸú·	CáÓo&ˆKˆŸØûJú53šÒîîÒ2ÝÑ´³T'5èç)Ko¢.š’µggžçtµ¦†òLìi¸[^Ù5ë~­ÎM>ówÕóoðØ†¢ënÇvË Î¼¯;$úÆž:1ª×§3›Kç³ùWS·óY¡¥Ãº:\n"
						+ "®\n"
						+ "…l%mN:ÔŒßWUOÅTmïÌºý:BW}pòÌ:–šÚþŽ1Ï~âvphÇôUNdŸÍÄººš¬øIÃ›}9ŽÀÕÒX»ËLt9hJêÞ5*u{É¹µ»m¶ßÅ‘5óÅ>á5·\n"
						+ "kèªTèâhEÆ¥”­èEBÚ¬ñje·VšéÑ°ºóƒ#m;ÕIsNÚ¼èÉ¥¶îqoñFšp         ­Ù++ù'ºy¶Ô/{ë9üyéI¯íF$©xô(Œ€                         \n"
						+ "´eÜ¬¸¬kÂN/ò]Z{¯ë®_ñ74         ¯ÿ 'þ\n"
						+ "ÒRÔúšWçwJÂSÞT ÿ 8äý9šŠ_¨ÉR­‰  ööÎÉQw—t-•j±£IÕ¨¡ÏR_f?%âÀú¿»µ°³«{}sFÖÚŒ\\êÖ­5B+Å¶ú$-9Â¥8Ô§%(I''ºiùú   <åí+³Ó¼mÔ¶7jÐ«r®Ô)¿è]Xª’¦ýrF‹œG%         m|¼üŸÅ%{¾Ê–f×w¿“«ÿ ƒdš‘€                         v½¿… sñœ¶wR¡oêåZ?ÂÎ¼ú4Ð        \n"
						+ "ã‚¼7Êñ;YSÁØMÛZR}xãº·¥¾ÝœÛéõ}!|[<xAÀ\"Ò8ˆÎêþŠæ­id•Zó©·Y×¨öŠ“ø½×’Û¡ËQþ[µýj®ÛòVŽöUºr®în•N{~¼ñJ1\\³}6{´‹‹ü§>ñ[Ä\n"
						+ "ÕQ‡ä»|uÅjwtëÔMÑ„2œšðN\n"
						+ "KáÕy1¾X]Û_ØÐ¾²¯NâÚâœjÑ«N[Æp’Þ2OÍ4Ó†8“Äû=I¢µ½††Õu°Yí-ÞÔ¾çµN¬éÑÝKºæ{rÊIGŸ®Þ‹tD'®õÎc[vKÄäò÷Ò¸Ëc5D-k\\tŒç(SJs{mïm(õ[uE_¨›Vê]Au¦åVçWßäe¨éÎ®bÒw2’S¥Uªjq}:¤¤¶òØ.,?5þ­þqxsÃÍ)ª+àluµ×§FªÖIC™I{ÑI'·MùˆÊvÑ¼HÓZXdôf7!<†[\n"
						+ "oß×¥G–ÝÏ~I(Ë~­KÅx/W³Ú2y|f2âÆß!}omZþ¿³ÚS©4¥Z¦Î\\±^odßÐW´h<;Í]élN.7Ù¥zuªKó4*Î^ìg³~æòèÿ Eyô,SÛ*ôieômý:[EU¯mu	½ö\\ÍBJ=7ß§1pÇyÄ%ÃÎÑzf¦¡ÑY[jz–Ö’Œj¸÷uÝU+ŠoÞÛÉKËÉµÐ‡¿/Ž¾Äe.±Y;Z–·Ö•eFâŒÖÒ§8½šfšb€       ;OÜ«-AŒ½ohÛÞÑ¬ßÂ5#'ýÄ¨ÂQœ#8´ã%ºkÍ‡è                         ±öüÔ*ßKií/J¤{ËÛÉÞV‡Ÿ%(òÇñ•Oì–,S²´        XÑœQž‚àÍÖJT•\n"
						+ "K¿©Rúú?jÒÞ1Œ!?Ó—¼Óû©¿=¶‰ˆ¦sIÊ¥IÊs›r”¤Ûro«m¿ýJ­ßTéŠo…Zjþö)fµÅ[êp’÷¨ØÓ%?üÉIËä‘­§³&¤v55ž‘¯=­µž»TâÞË¿¥FrãSðB¥NÝ„õ}\\×\n"
						+ "nôÅÕW:ØÑT9¥»öz©Êä¤¦¾[¥V7Ê®7zÚ•¥YÑSÉÜÓŸ#Ûš4¢ýSßª+Q¬~Z¼þJ­8¦Õ—·»ùGµW»TÓúE?ÅÖw9I•Ée,r•nªÆúÊÖ…µ*ñ“SJŒ ÷ñÝ$ˆ,ù?©)ë\n"
						+ "Ys.³V4#¿¯5I·ýÂ§éÛqW½CÛ;KaÝÊŽ3LÜ8õ{EUîeV´ßÉrÇêø­¼EÔµµ†ºÍjzíï‘»jqoìSð§¤WÐªî¸‘¥èXé½)¬qPKŸÇÆ5cº¡{EwuáãÓv¹×Î^€kÚ3Sfô~¢¶ÏéëéÙßÛ½ã8õŒãç	¯½æ˜O5^']jÛ=] ­n²Ú?”í’éJêP–ÏÍ8¨4üvñê!ñ@       É.h¸½ökn€z]ÀíCSÂM3šM:•l)Ó¬·ð«MrM~ôY–+s                          yóÚ×U­SÆ¼¬hÔs³Ä(ãhuÝo\n"
						+ "ÝF¿mÉ~Ê,jq•@         ÍÁZ[ßæì,nëÂÞÚâæ:õ¦öTé¹%)?’Ý³qŸXÇ[ëË¬¥9PÅ[BXªÍZÒ\\´ÖÛôo¬ŸÆ[y¬à²UñjoéhómñR‹‹_U&¾ M}ˆ3´0|OÊR»­{ØJÕ.*Míq£(O™¿$—7â*TUÅÝ®¥â>¢ÔNrµÈdkW ä¶n›—ºöò÷R\n"
						+ "×\n"
						+ "  °…õ.3\n"
						+ "Äœ–!uz™«HR³ç{F¥ZrräßÕÅ½½vØ•*!Îj;ßç;¨”›ºº¼¼|Ï£Ú«œýÙk)$¶]Dƒ¥u5¥ßõ&ÌUQP©Æ¤¶Új5i|9é¶×Å?Rø           .`}V®tæsF×¨»Û\n"
						+ "êöÚ?÷U:Oðœwý²VjÏ                        ªqsWÛè^f55yGžÖÝ«h6¿9^^í8ýd×Óp<Í¯V­Åz—JÕg*•&üe)=Ûú¶ÊÛà             å£^½Õ³§Ðtê(Ënx¿¿UÑtˆ   }Ð«R…juèÔ•:´ä§	Åìã$÷M?&˜“œ§9Nrr”ží¿Àù             {;ë5¡xµ‡ÌWªéØV›³¾~J]““ýYrËöH•èúi¤ÓM?ˆÈ                        )wmþ\"G9ª-ô&2ãšÇ>öùÅô©t×H?Ô‹üdý‘\\\n"
						+ "                                 i5³[ /Çdn#C[pâ–*þáK7ƒŒm®¥ïU¤—æªüwK•¿X¿S,Øš                      h¼yÖ9\n"
						+ "ÂÜ¶¥ÅZBæöÝBUH·JsPç’^)o¾Þ}Å-Ëv†âþJŒéOWJÖ3ñV–ti5ò’2üK‹‘V©RµiÖ­Ru*Ô“œç9o)I½Ûmø¶üÊ¯€                                 ÛJj\\þ”ËG-¦ò×XËØÅÃ½¡-·‹Ûx´÷R]F™µ£»KqV×=†G%mšµÅ:umêÙS„ªFRI¨ÊœbÔºôñëä11|È                    ¯Ô¸\\v¢Ó÷Ø,µqc}BT+Ó~q’ÙíèüÓò`Q,vz×Z3#^®'s¨p‰·FêÒõaJ”×¼šõI§ðð.µ*'½Æä¬—5ö6öÑsrï^Þtúú{Éb”                                 ¶/LêL­zt1ºw1yR®ÜŠIóoèÔvØ‚Ìökìë–°ÏÙk-}oOc’­cŠrR›¨ºÆ¥]·K•õQÝ½Òom¶–­‰                      F{iëßå7£¦lkóctút¦¢úNê_Ò?Ù[CàÔ½K‘•@                                ãìS¯^¤áíM/¸ï2X©Ãš[Ê¥¬¿£~¯•ï’©šÍO¡                       i|rÉêL?\n"
						+ "5GI[Ô­˜£m½îÓ‚rJsŒ|Übå$¾ y«R¬ëV©V¥IT©9¹T”¥¼œ›Ý¶ýwñ+o’€                                tà~oSá8¥„¸Ò­q’©s\n"
						+ "3¶¦œ•z2’U#4¾îÝwòÙ?\"éa                       í‹Óø®\n"
						+ "e²”08šy;Ë›{lVtÕeÍV.^þÜÛµ¼|ÂÅ4Ð                                sãë«\\…µÔ£ª¡QÆKu.Y'³^iì¨z{§±´UÖŒÇ«ˆ)¹ZZÂ—:}zò¥¹–°                        ¯=¼ï;žbì÷ÿ IÌSéê£J£þý„X¤¦š                                š©ºrKÅ¦£pöå^è-=vžýö.Ú¦ÿ :QfXw€                       ®”ê+¤ly½éÞ\\WÛáF?ã,X¨Eh                                 zIÙê÷ò‡4}Óñxª08._ð™b·À                       Iûxgmò<KÄámëª}üSÝB¥Ysr¿,`ÿ i5Üª                                 /bœý·­qŠ´%s‡º­mVš~ôc)ºmz5>Ÿ'èf³z›Â                       FøÕÙÿ ˆìÖ¥Õõ/±ùLJÆFµí[®JÊžîmJ]d—M“{ì¶ô.µ**€                               c¦p×º‹PX`±ª›½¿¯ê¤ù\"ç'²MùnÀ¸]”¸/­8w©rYíM{imJæÓÙ£amYÕï2’œÞÉ-¶im»÷™–mXÐ€                       A=µ5¶…5tå:°yü•Óß¬hE©U›^Kd£ûB,QSM                                f#s‡ÌØåìÞ×674îiuûð’’þ(NôF£Çjí'ŒÔ˜ªŠv™xÖ‡]Ü[ûQ½Óø£,;                       ëõg§°7ÙÌµÄmìlhJ½zÊ1[ý_’^l7x½®ò\\F×Wº—!Ï\n"
						+ "s}Õ•»{«{tß$>}woÍ¶VãQ(                                 ,?c>)½5©?Ù«±j»ÙÎoÝ¶º~_Ôðým½Y*X»$d                      eíï©î¬´¾IÛT”)äëÎæënœð¥ËË—<“ý”X±R4þ/¨rpÆ`±—y;ÙýšÔœåó{x/‹Ù¤£¬8=S†ü7ž¤×w4Ök!5m‰Ä[Ôß»›ë*•¦¼yb›åMÚÝ¿$¨|ª                              F6­­…½këi]ZÂ¬]z1—,§\n"
						+ "ýäŸ“Û}¾ KüAà¢Çb¨jêê­1}F7VÓ£ít©J<ÉNšû[/8þšš†š«B»_œ¡^”þ19¯âšUég5-Æ¯á6›ÔWn®ì£íôªA¸N_YE¿©–                      5½u ô†¸£kKUà­r±´›÷™:møìâÓÙì·^ gi5§ôÅ‚°ÓØk]·MákF0æø½º·ñ`R~Úº¶Yþ.ËF·5ž‚¶QO§}4§QüúÂ?²XÔA¥P                               ]¾Âú²yŽ^i›š®uðW;RMõöz»Ê+ä¤ª/–ÆY©cYðÓAkÊ¾¤Ò¸Ü…Âÿ §•>J¯ç8í'ø„ÖÃ„Åã°˜‹\\N&Î•…¥5J…\n"
						+ "QÚ4â¼@f                      ®ÔÙ¼n›Ó÷ÙÜÅÄm¬,hÊµzÊ+ÓÕ¿¼Û@Q>.v…×Öú½¾&þãO`›q¥mi7\n"
						+ "Õ!ëV¢÷·£—Ì¸Ö!úµ*V«*µªN¥I½å9ÉÊR~­¾­•_                                v/–Â^Æ÷\n"
						+ "“½Ç\\Å¦ªÚ×•)tðë€´]›»Fä¯³všCˆ74ëûT•+,´’„•GÒ4ëm²{ø)ôëÑï¾äKÀŒ€                      ¬]¾µ%k]3€Ò”*òÇ!q;»˜¯½\n"
						+ "I(/—4÷ý”X±O\n"
						+ "Ð                                  ëâ›‹]SOfŸªÒÞjJÚ·„ºo=u>{«‹(Æâ[ï½Xo	·ó”[ú™b·0                      SŽÝØüÆCˆú~6˜»ÛšÄÉSœyY9¦Òil”_É–5˜ª                                  sÙZ]^×VöVÕ®k4ä©ÑƒœšKw²]^É6óìeG#oÀœ},­Å´•åË£ÔÜ$éº§³ë³m™fõ3                      Œ{RjéÎê+ª5¥Fâê”lhÊ/f¥ZJoÙr@±çi¦€                                 ó@çkiq„Ô&á+êU¤ÓÛÜR\\ëäâä¾¤¡ÂQœ#85(ÉnšóDaú                      \n"
						+ "ßÛï#::Àc\"ýÛ¼·y/Š§Jñ’ü)yZ                                  ?'(8¿¶`zwÂŒ‹ËpÃKä›ÞW›iÍÿ YÒŽÿ Çs,6`                      Uÿ ò‚Ó›Ó:Jªû1¿¯ótÖßÜË)ùZ                                   zOÙþÞ­·´u*ËiþH·—ÒPR_Á£,Öò                     —öôÔ’½×¸}/J«tq–^ÓV]íföú¨A~ñcQ[Ê                                   óØÓRO?Á++Zõ9î0÷1òë»äŽÒ§ý‰Å}³z™Â                      yÁÚ;+S1Ç[u9¹Æü­ ÿ «J*šÿ p±¸Š                                   -Où>ò²þ­Á?±:v÷‘ø4ç	~)Ãð%J¶äd                     Ì.(F¬8•ª#_~ñf.ù·õï¤XÜk…                                   7°*›â^zQO•aö›ùÖ†ßÜÉR®™                      ¤Ý«x;©í8{ªtî÷+ŠÍTU¦¬èº³¡]¤§F+}¤ýäöóh­J¯uéT¡^¥\n"
						+ "Ð•:´äá8Ilã$öiüS*¾                                  3ð¸l¾jâvø|eæF¼!Ï*v´%Vj;¥¾ÑMí»D[±Ï2ºLä3šŽÍÙåó\n"
						+ "6Óût(Cw/IIÉ¶¼’žäfÔö                     ò[ò¾]·Û¦àyQ’ï¿)]ûG7ßÔïy¼yùŸ6ÿ ]ÊÛ                                  ›»{GóïoÜ©÷“.»í¼9}Í·ý®RT¼_\"2                      ¹v¶ã_òbÂ¦‰Òw±ü¹wM«Ûª5v4ŸNU·…Yeuñh±dRÎ¯«m¿V÷eh                                  í´~£Ìi-Gg¨07“´¿´Ÿ59®ªKÎ2_z-tiøz!Á>$b¸™£(f¬Ý+{è~nþË¼NvõWOWãæŸÌŒÙè                     «vâÖº×—Ã`pù+¼^úÊu*Ô¶—$®*)òÊkªJ./dÖüÝK*L›”œ¥')7»mîÛõl­?                                    ËÄdòX{ød1¼}å?±^Ú¬©Í}W÷èOf=I©5_19­S7Zú¤êÂ‚Œ®)Fn0¨Òé»KÅxí¿™–jL                    k<GÐºkˆ~XMMcí¹éT„¹*ÐžÛsB^Oø?4À­ù¾Ç—>ØÞ\\RöfúFöÅº‘ý¨Kgø\"ëZŠ8ïÂü7;-S<Î~é:Õ¨S·T©[Ñ]~ô›”Ÿ‡‡H¾€—Q•­¥ÕÛ¨­m«Wt©Ê­EN\\Ú“ÛÂ+Íøp”                                 ;:XqDXj%®cN5ãÉuk{eÍ+jñé89B}v}WO™55\"hNÈø;êWšÃPÖÌÂwemG¸¥?„å»“_Ê4þ–RÂÒÖÂÆ…•½+k[zq§F•8¨ÆKe—‚HŒ¹€                     q/³V\\ëûÝY{ªóÎöPum¡NœÔTb£´%%º[/\n"
						+ "žÁu\"ðã†Z3@aêãtö\"œUÄy.®.?;ZåzT“ñ_Õè¾5O{XðžÏ‡Z¢×)‚\\˜,Ì¦é[·þ‹Z;9S^°iïN«É5=BeP Ö6·×´,­)NµÅz‘¥JœîR“Ù$¾líf¼­oÞF§u7(¾g¶ëà                     ˜ìu{ú7“¶\\ò´ î*CÏ»MsI|·Ýü:a” ;vvàµ‡8{©ïn®+Xä(ÜÓ¡‹»[¸Fqƒ”Ôã÷¢ù ŸšñDKqkÍ¨´>~®RãjÙ]C¬$úÓ­Ó§/	Gû¼ö\n"
						+ "ãÐö¸ýScÔ×—V8»ªŠ[»~W+w.‘¨Ô–Î)í¿Ãp/×¸IaÂ›õöYdªS«7^1„!Êš\\±›O«óÙ›u%„                         \n"
						+ "WÛ»U~Râ3JÐ›tpÖ½íeåßVÙíô„cûÅEs*€InÞÉ/é½7S‡¼(¹âVr—q™ÍS–?L[T[Nš©ª^4ü§ÍÉóßÍ;â I%²[\"¨                     q¢³ÕtÆ«ÇgiÑÄm+)V¡5¼kÒiÆ¥6¼Ô å©ÛÆþGFå­rø9NïHg)Fëwâ”$¹»™µÓš;ýWÅ1]GEPÝØ×Z`sü5†Çã­ñ™\"Œ/(Ñ[F¿6ûW^mËgÍ¿ƒ^›f¥Ms£´Þ¶ÂÏ©±T2Ïwu´éKô¡%Ö2ø ŠÇ­û!_Æîutf¦¶«k&Ú¶ÊÅÆp^äRúÅZÕ›áÖ')‚Ð˜L.nööFÆÊ½ÅÄ7å©(Å-Öý|¼ÈË¿                          PÎÙ_+ŒãeæN¥µ_bÎFK;‡á)¨Fœ©ïúIÇ}¼vh±¨ß5×d‹·NÎ¶ŠÏ[¹+xBê†IÊ<Õ÷ªBq‹èßÝk§¨ÓU¶ïNå)ê‹­9gi_%ou;^KJ3¨ç8ÉÅòÇnm·^VK€Ý›ei*z»ŠP£ooj»úXš“N)EssÜK}’^<Ÿ¯È%¨´w?œ}RêÂN8l]®.žÛ'ýê›]¤×¤TP‹&#\"€                      ,Ïe]U§õv•¼à®»…;›:üÕq.´¶}^ò¥	}ÙÅï85ë%å±*_õ¥ñ›³Þ°Ð·U¯±÷‡»”.m©sV¡J´×^Ÿ¥×®ÃIZ	¸mâVvóƒ¯eowioíä§òs(½š‹ë¼—@¶®g.\n"
						+ "[ð«{^ï!Žo\"¡ªÔâãJœ#¿, ŸV·m¶ú¿·RÐ@                           ÕµµÔ#«zUã)Æ5 ¤”—ƒ[ùü@åŽ½½:Ó­N…(U©öç%)|ß˜ûµïµåö~÷@Ûa28œ&£*ÊŒÜ²k£ÝM-»½þêêÚëèXÔŠË8Êpœ\\džÍ5³Lªü                       œw·Âî×s›y*°©o¿=7º’kªÙíÔçÙgˆz»]ik˜jÜÝ½{HRÉÎƒ¥Nù4þëKß[-Üz=×‡–lKô­íéTZT)SN³”`“—Íù„r€                               ?jÝS©4Ÿo/´Å*ñº¸¯Z·t¢Ü¬éM>j‹oÑEKÉËp±ç¬«Ó“s•hÉ·»”§»6i§<íî!oNæt*ÂFÕ:’ƒQ›^;?ôˆ                 rJ…hÛÂâT¦¨ÎN0¨×»&¶Ý'ê·_ŠŒ   r\\Q­oNKŠU(Â¢æ§*‘qS^©¿òµÐº›9¥µMŽgL\\ÕŽR…DèÓ¥¼oZn+í)x5ñ ôûq^ïiusm;Zõ¨B¥JûT¤â›‹ø§ÓèF                                 ÉÆ3ƒ„â¥-šktÐ*ÒM\\;…¥ðŠ³êê+\n"
						+ "\\ß.à`ëŽhÍmigi©°×ô¬œ²æ•7K›mù\\i=—O€yñkN^i~\"g178z˜ª4¯jû%'r:ï»p”¾ÒqÛ®ì­µB€‡á–O3¡óšó,ªã´Ö*Ê­h\\J;Jò²MS§Kæâœ¼<–ïÂŠ             _gÙèK¤s¼3Ö×ëu’»…æþ¦Ê+ª|$ÞÊOeî½”–ë}ö%JŽø•¡5uðšŠ×’oÞ·¹§»£u)Ó—šõ^+Á…k '®È<5Æëlþ^¾¬Ó5oðTm£^£©JÑÎºFQk™òón¼ºRÕÏ°ÓzÇi¶ÂØ¬]5NÞÖtTéÓŠòJ[‘—î7Lé¼eÇ´ãtþ&Ê¿úË{:tåøÅ&l                                    \n"
						+ "ˆz?®´¥æ›ÏP•KK˜¯~j”¤žñœ”“KûŸ@*îS±îv7óX½kŽžþãº³šª—Ç•´ßËbëZß8oÙWH`®)_j»êÚ’æI[¸w6ª_¦å?«Ûà55öÅâ¾3'oK†ÚN¥)cìêÆYÔ6Tœ¡ö(Cn›Eõ{tÝ%äÄY”ª      }JœãNP”cSw	5Ò[=žÞ»0>@     \n"
						+ "&šktÀÛpÜAÔv8_äýýjYÌk|fN.µ(|iËu:R^NDÁÛè%¤¸ž·Âaóµ´¾^î\\–Ö™NæÞ´¶ß’¡´“èöS]¼XÇ{%ÐÆçmò:ÏQQÉÛ[ÔUS…Vº¥9ÉïË¿ŠIoá¸ÔÕ¢§S‚…8FŠÙF+dˆËè                                       N{Wq£[RÔ™=µ»Óx»y:Un¶p­¾Ôg÷iµáËÕù¿\"µ\"¹aq\\ÕÝ;<>6ó!qRJ0§kBU$ÛøE0­–§\n"
						+ "¸™N§w-©y¼:cjÉ~)l\n"
						+ "qçxq¬´î\"9mM†©‚´œ¹)Jþq§:ÓÛ~XSß½¾/1¦µ-Ò‹['ñ(ÌÁâ²YÜÅ®eV÷!w5N…\n"
						+ "KyI¿îKÍø\"\n"
						+ "‹úzÓHë9é[kˆ\\ÕÅÚÐ¡yZe;—MN®ß)ò¯‚kX¬mþVâ¥¾:Ö¥ÍZt*\\NÖíS§)Ëä¢›`bvZ_Ö¦Åaê]FÒ÷´m¥^KuMNj._M÷ » ¸;ƒÈðBž7OØÆÞïKZJ¶1Å{Ò„Või·çÎ“­³#2¨Ši­ÓÝ3M  ß´¶†»Õ¼,Ìf°Vò¹ÊéûÕ;»jqæ©VÎ¥>“Šñn§.‹É¿B'Ö‚½J®Z…{ˆÂUi[ÆOny¶£‹ô@nuøIÄšt)\\ÐÑÙKûJÔÕJ6WTªÁ¤Ô£*N[¦™4×ÍŸ	8¡w5P¶üêYÊ’üg²¥.öXÕù\\úâtðX¤¹ªR£^.ªzEmÍ|Û!©«Ã~ðïAd!“Ãâj\\ä©ÿ Gy}UÖ«Ou³äðŒ[õI5%    *Uo¥j¡$Ó’R{m'·_Ndh     6Fî6VýôãÌ·Û¬ÔWÕ¶’ú€´¼…xMÊ¥*i9ÆM=“[§ºèÖÞh¾ç=N›‹éÍsFU+Ó§Ì½R”“Ûâm<F¤êÆt¥KnxIo.¾mã¿–ÀaË6Õ×³û\"çrQåw4”÷~\\®[ïð&¶V„,UÔ\"äœœydÔZñMËdšÛÌklí:ÒkÙ÷Q[Ë»¯N£K×–2oøÛSœjSHIJ2[¦¼Ð@                      à»³´¼‚…Ý­ˆ§ºUi©%øûkkkkK[j4#ú4à¢¿€Àk<DÐš[_á£ŠÕ8ÈÞÑ§>òŒÔœ*QžÛsBkª{}˜-NÉœ7uùá‘ÔP§þ­]S{}]=Æ®»<Õ>vpÒ5ò8Üm—¹¦ák\n"
						+ "•;ÛËÉùG™õŒñkh¯žÀêŒç²·ÙÌÝök'W½½¾¸ÅyúÎM··Ã®Ëà‘¦—±çg‚À]êýSaË˜·vöÖµ¡Ö•¤¼\\“ê¥><¢—«2Íª±Åm%q¡¸…˜Òõã%;‡ìòknz÷©É~ËKæ™cMnÖâ­¥ÕºÓP©´ÿ Z-5üRÔ}/”µÔºK˜¥ÉVÛ%eNºKªq©Úþ;‡˜Ú§,6§Ëb%Ob½­n—¢…IEVÝiFrÄdå€ž~6U¥‹…Ò´Ò‹pgeü›^¤ßgN$ÿ 6\\A†Rê*â/i{.F5¼”7Þ5\"¼Ü_]¼Ó’‹a¨¸Â%Ó£ªqÔº¾|¯0õÕ:u÷ûÎ;8ïëÑ=üz‘uØžÊ|0³¹…kÉæòQ‹ßº¯v£	|w¶¾£MMø»,V6ÛŽµ¥kgkN4¨Q¥XÓ„VÉ%é°FH       ­XÖžEFÚ5{ÿ h¸q”+(l¶§¾ûÆ[ù•g’¼’§l¹¥V¿Jr¨“pjRŒ÷Ûe-¹[O¦è*5²<´.»êð…Ì’¤êÕŒ£»û<ÑP[oáÑ½·óŠŽB÷#*U-ªWÞ´ªrÂ5£Æ1Qõ„·~ð\n"
						+ "þõÂÞ„êU‡´TPS|¼ðÚ|³[¥³ë¶Ïeãà…ÎRTíëEÜmqè÷•aR-¨¹rÉ(E­Ò}S{? >¯2·téV¶§yÝUmSîÉ(í»”¹eâÞÉmäú÷o‘¾TeÐ©qÊ©ºð÷é¾nYnºo·Š{-Ó@sáÝÝÝ7q[¼•­X7ÖœdÚò{(­¾[°>,©Jµ”¨S|®xÊ1Ã¤ÀÇöÚt.njNÎÞn¼¢ÜjU…9ÃhF.-Om×Nn€ÇK’ÒÊtiÆuåÎéS§QMIÆ²”!Ìºl·ká×Ðª‘£SZ‹¬ÝG)Î´l¥6“m½¥ççïmñ:ÒõB…X×£JWU®%½.tà½Þdù¼×*Oá¿@0árîîhJ4h(Ò¨ªEÛR¨ç6¾ê“‚ŠOÍïàˆ·¦2ÞÞ£Þtà“ù”                          qŸ³‡u­]Rõeþ>âµ(R©BtzqQ[.ïÞ‹‚é»]Ví¿0²³xcÙ»@èËú9K¸Üê•ÍJ­ÿ /uNKÂQ¥Ë¿Æ\\ÀÔÐ\\;mðÚ¦wMÐ×x‹wRÿ MÓ¾„½R×}ù¾.\n"
						+ "ïú²—¡bÅ.+K³Øƒ][æ8SFÝÜÅdp•qNRIÔ¶›r‹Šóå“”_¢å%fÅ|ã~•¿Èv–ÎiÜ]þë)•„­ãM¦Ÿ}É¿‚\\ÒoÓfqj¼=}=ª2¸¦¥[yVÖrKe'Nn;¯ƒÛ¨UÖì‰¤¬*öx§o™ÇÛÞZçn.+×£Z\n"
						+ "Q©O›»Žéü)¦¿3zéõwd!¾•ÎÏä°“mÛN\n"
						+ "æœU¶¤—ÎLºjMà_¬øW§nñ6™«ì§µÜ{EIWJ„¹RÚ[¨§·^­²êB        ¨´±¯O-ßÊ)SŒêÉ=üyù6ÿ uÃeÜkÂ¬6„è9Ê\n"
						+ "µ´Üª9mòqm}@á£i“J…J’§A§JTTb×ƒrRÞIxíÊ·Ùx‹go+ZÔm­']V§V¼`áNN;Sß~i/‡ñ*VuèÊÊ¥~h¨ÜÆ0Œ¤¤ýé¹JM¥¶ííÑt[Ç‹·½­ehí§péB¸•UwiÅÇ™¨¶äÒ~—¸Z©S£V¥\n"
						+ "NN“„–ñæÛx¸½“[­÷ßu¸vxº´#\n"
						+ "Šƒæ…hOªŠ›K}×I5ü@Î£oum†·¶¦ßyN\n"
						+ "3pI½¶òß§ŽÀqZP¹¥^ƒ¥Nêš…teÞª|²Œ[êömïÕøûs„ªËiÛw·u\n"
						+ "N;oÓ¬žþ 8mqwTáJ´Z§RÝ·Fœ§Í¾í¹¹=¼e¿’ÙyÃlŒ-•”ä-ù99\"©I¥è¦ß‡ÅÇp?jb*¸¹Î—ô±œ'NŒ“p‹§Éœº=’ñ\"”2áFWü±I/ÍÑÿ ˜ÒÏ¿öZ~ÓËßrûü¾Ê                             š´éÕ¥:Ua”çFKu$üS^h\n"
						+ "aÚ³†_“¸Ô>°«‘ÃVn¥\\uÍZÍù¨GÆtýÞKÃfŠÔªýoŒÌ{zµ¡È{nüªŒ-çÞïéÊ—0UÌìÂŽŽ²»Õº²Å[æïâ©ÚÛÕŠu-(ø¶ßÝœÛê¼’Iø°Í¨g¶¦Š¹Ó¼Q«©h[IãsñUcQ}•s£87äÞÑ’õÝú±r8YƒŽ›á¾ÁF..ËB”Óñçä\\ßÚÜŒ¶@             Ô¬méÝÊê1—y-þóÙ7¶í/-ö_€wvôîi*u9¶MI8½škÍ4Ý½*t(Â(òÂh¯DØ                                       ó–<üü«›m·Û¨ cd±ö;ofÉXÛ^ÐæSîî)F¤y—ƒÚI­×¨                                                                                        ÿÙ', 'Default Profile', '1', 'jpg');";
				statement.executeUpdate(sql);
                                System.out.println("ASDFASDFASDFASDFASDFASDFASFD");

                        }

			System.out.println("Initialization Complete");

		} catch (Exception e) {
		}
	}

}
