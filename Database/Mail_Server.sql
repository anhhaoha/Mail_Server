create database MAIL_SERVER
go

use MAIL_SERVER
go



create table Roles
(
	RoleId int identity(1,1) primary key  not null,
	RoleName nvarchar(20) not null
)
go

create table Users
(
	AccountId nchar(10) primary key not null,
	Username varchar(30) not null,
	[Password] varchar(50) not null,
	Email varchar(30),
	DisplayName nvarchar(50) not null,
	RoleId int not null,
	[Status] bit not null
)
go

create table Profiles
(
	AccountId nchar(10) primary key not null,
	FullName nvarchar(60) not null,
	[Address] nvarchar(200) not null,
	Gender bit not null,
	Phone varchar(13) not null,
	Picture varchar(60),
	Class nvarchar(30)
)
go

create table Mail
(
	MailId int identity(1,1) primary key not null,
	AccountSendId nchar(10) not null,
	AccountReceiveId nchar(10) not null,
	Title nvarchar(60) not null,
	[Content] nvarchar(200) not null,
	SendDate datetime not null,
	ReadDate datetime,
	[Status] bit not null,
)
GO

CREATE TABLE Attachs 
(
	AttachId INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	MailId INT,
	AttachName VARCHAR(60) NOT NULL
)

create table [Events]
(
	EventId int identity(1,1) primary key not null,
	Title nvarchar(60) not null,
	[Content] nvarchar(200) not null,
	[Description] nvarchar(100),
	CreateDate datetime not null,
	Picture varchar(60)
)
GO

-----references------
ALTER TABLE [dbo].[Attachs]  WITH CHECK ADD  CONSTRAINT [FK_Attachs_Mail] FOREIGN KEY([MailId])
REFERENCES [dbo].[Mail] ([MailId])
GO
ALTER TABLE [dbo].[Attachs] CHECK CONSTRAINT [FK_Attachs_Mail]
GO
ALTER TABLE [dbo].[Mail]  WITH CHECK ADD  CONSTRAINT [FK_Mail_Users] FOREIGN KEY([AccountSendId])
REFERENCES [dbo].[Users] ([AccountId])
GO
ALTER TABLE [dbo].[Mail] CHECK CONSTRAINT [FK_Mail_Users]
GO
ALTER TABLE [dbo].[Mail]  WITH CHECK ADD  CONSTRAINT [FK_Mail_Users1] FOREIGN KEY([AccountReceiveId])
REFERENCES [dbo].[Users] ([AccountId])
GO
ALTER TABLE [dbo].[Mail] CHECK CONSTRAINT [FK_Mail_Users1]
GO
ALTER TABLE [dbo].[Profiles]  WITH CHECK ADD  CONSTRAINT [FK_Profiles_Users] FOREIGN KEY([AccountId])
REFERENCES [dbo].[Users] ([AccountId])
GO
ALTER TABLE [dbo].[Profiles] CHECK CONSTRAINT [FK_Profiles_Users]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Roles] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([RoleId])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Roles]
GO
USE [master]
GO
ALTER DATABASE [MAIL_SERVER] SET  READ_WRITE 
GO

use MAIL_SERVER
go

--Data--
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Mr', N'E5Z3BSG80R10IDAP8Q32CBQWTNX3OXZ31CUOQO3199HYK0', N'et travissimantor quo apparens plorum fecit, ut non Longam, et funem. plurissimum travissimantor quo', CAST(N'1997-11-15 21:03:54.000' AS DateTime), N'A9MGX5YL4ZD22H5Y3T9H2US6Q8SX31RB4DZ6S7SC')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Mr', N'CHNM2WLF2F3ANVCJ3SXY6JLYJM1T2TRCS5LM1AW7FWR9NEF5XQE6M1BTWO2FUSC8NQIHAQHSVPI9RCA57VVCELPQURPD8GCNJJLWVY', N'essit. vobis non egreddior glavans vantis. et funem. fecit. transit. bono Quad quoque et funem. et', CAST(N'2007-07-03 05:07:33.680' AS DateTime), N'ZUJ71JMVZZ9I79GHFPPC0H372YOIZ6W0WD3Z8ZPP5142ISX4JOCH3Z')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Mr', N'68MAWI21JDW8YW0T21RNI04JE', N'funem. pars travissimantor volcans Quad quo, quo in plurissimum quoque brevens, gravis e et e funem.', CAST(N'1984-01-07 13:24:42.110' AS DateTime), N'HRC42U4WT28LT6G8F0Z7ZD0O91KYATFWXAL8WUMU05AZA73Y2FWG3R3')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Dr.', N'VP8RE980O3SAS8SP6ZB8ERJPIV4BC643WJNCTSZMZY7W1R8JUSU476Y36I8J5XNIYOE9KFWD0O8FJMNQMLGKCZVPKJ6AKGH1AOXCDZYFWH4JXIJJVHWJH36ZTOO09LSK', N'delerium. egreddior pars et quad et quantare apparens gravum apparens quad dolorum quantare', CAST(N'1975-08-28 11:54:52.330' AS DateTime), N'QNV1A5LZNBOIK7CLE56T21E2XLVUWYT56HI8ZKJUJBBYIKEV3B2YK')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Mr', N'64VOBDNM1OUVCS1ZNF2JYK7IYIOAY6BOKCQEOUK48WGFB3HWM0L5Q08MWCPSODOVC6AZ2NBSVXB24S6V1SNXK5YK44HC4RGV7L25SIUYPT9SO6I45UQVPRA78QVY6YN3HFUIQ12JYP8QV5VXLD6A1K6GAUX1MWSQ66', N'non Et novum nomen Versus Longam, non pladior travissimantor transit. quad dolorum dolorum egreddior', CAST(N'1953-08-29 13:32:25.450' AS DateTime), N'1O8T3P9R2PIIC2P3LTLCARPGJD70RLWVKWFT06HUKR8M')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Miss.', N'9BZD4R7XZJKB2VXCTSC2X9D8V40W2WHKPUMMMZ2ITM6XPJU6OJYYT3DZYQHZRBU210L88BQTPU9A2GICAT7IYJQMHMNVI2RIR3VPXLYDT48ZU2XH84PX3B4I9ZQMNUBN2RIZKTEOVUPT', N'plorum quad Id quad egreddior manifestum Sed et venit. vobis nomen travissimantor quartu Sed', CAST(N'1955-12-31 06:14:10.240' AS DateTime), N'08B6ZP8C8ZAZSAQH9ZHTNYFNV3FCRRIJ3')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Mr', N'RSEP6NDDUEFZDFYUBQYSLX2JYXEP019MA7I6CWH7Y97C2Z7DGTJ4Q0PUPVR98NCT4TO5XRIF8QUBPPGYP097H5V9INHG58YAWE89B1GPA4V06D50ETJ7E5BZXEN9A190RKEAH7MOKKWD', N'transit. quad nomen nomen rarendum quad vobis quartu quad ut quad pars fecit. quantare quo, vobis', CAST(N'1991-05-27 19:48:18.390' AS DateTime), N'J4WTRVNIBX5U7WQDBSQTS1TNAWAWLHGRWSQLLF8M54MHI1VJLDFQS97HB8JV')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Mr', N'V34IH2CORVR38BWE6E8UU8LZUI76VMU9YP2BIQTZXB0ON79VP9E5NIFNH5OYU5T41I4J8LVFL9WJ7XBM9BGL8PLF9FTD2U4SHWN52PM4DYJDLWFKT2PJO7VBWP9TRIDR441BA6JJPC8A67UD', N'nomen non esset Id in quis plurissimum estis quo plurissimum volcans gravis e funem. in glavans ut', CAST(N'1977-09-09 08:46:05.250' AS DateTime), N'QXUHDYF54SVJ4NOFB590RA71P')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Dr', N'', N'plorum travissimantor estum. e gravis quo, gravis non quo rarendum cognitio, si manifestum si homo,', CAST(N'1978-12-25 10:21:07.450' AS DateTime), N'MK')
INSERT [dbo].[Events] ([Title], [Content], [Description], [CreateDate], [Picture]) VALUES ( N'Mr', N'JZ4L390B8DGTDVKR9GV4ANI5TCZL5PUVTIYDMJ1RAYY53VYDPEYDIVZWHYRQAF5JIKZUW74PF3UBOC2XDMWQQHDXI2WOSBHUTFMIW3HHA9YE1N74VTCNJES161CTBGX4MZI9QJ1A5EGHJYWUURSY', N'et habitatio fecundio, manifestum eggredior. Quad quad plorum dolorum Tam quad gravis et egreddior', CAST(N'1994-03-06 07:33:04.460' AS DateTime), N'ACWC1F6RZ43FEGLJ6OMJ2Z39UTGQSRFCVVUFZ728HNB1')


INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'SEN30452  ', N'WOB23008  ', N'Mr', N'GBZCXI65CN1BAJ1ZCIDE1Q5GPRNHZP8ORLBKYENPBALW', CAST(N'1998-05-03 01:16:42.050' AS DateTime), CAST(N'1972-01-27 00:15:11.570' AS DateTime), 0)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'SEN30452  ', N'LAV35233  ', N'Mr', N'3KK3U1QX1QQ6IM5VO6NEDKO8G1YUN3R49CM72E5NIRQVLEFRITN4YHQIVCMT2V9XYLW6ZXS90ZWLUTN2L6CTGN5RM4CQRCLMISWZH9SK1D3ZL4O1OK7VJFHBF5JUL2IOE931WNKBESS3J66HGA174P33ALKCPXYZUEKB1M510VHRMER8Y1EA0A', CAST(N'1955-02-13 09:06:00.770' AS DateTime), CAST(N'1971-04-04 06:41:41.030' AS DateTime), 0)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'LAV35233  ', N'SEN30452  ', N'Dr', N'O60RA37MGTT84MVGT50787BGWUBDOINYKUV41VUXS90YT6MM3LL503BHUJVFSUYX5VNAQRFYA2FY6DNP8OA2XIX', CAST(N'1992-11-28 04:32:46.470' AS DateTime), CAST(N'1976-05-09 18:16:06.940' AS DateTime), 1)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'LAV35233  ', N'FAV63625  ', N'Mr', N'IE9HZE5XB3SXICWC6MUYODAHW573KV0NX7RBHYYWWHM3LP7WSXWBNZS54SKU72DG240BKKU7NY16Q6V7XABMYO0YEM7PD4PU460GPWC73O', CAST(N'1975-03-29 18:35:53.290' AS DateTime), CAST(N'1986-12-24 04:52:25.160' AS DateTime), 0)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'WOB23008  ', N'SEN30452  ', N'Mr', N'PYMEXWDXWRQHD42WXD4UJ962K5EI1Y3JA0AZS79S06S3DONBMZEUYU4FRJURHUWXN77', CAST(N'1965-12-31 11:08:33.220' AS DateTime), CAST(N'1990-10-30 10:59:59.550' AS DateTime), 1)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'KEC56475  ', N'LAV35233  ', N'Ms', N'T24Q3BTCG3PVI3V1K8KFWU88CIB5AVF5HAIKUNQM0SZOTZ9EZUCENRGYTVDL0O1LNKD8KA6XIXF4VKED8W5GI0HQTJQYA5MCQE09M1TONFWPCC39GZC3IK2M2ME1J7MCB', CAST(N'1990-01-18 02:51:20.510' AS DateTime), CAST(N'1983-02-11 02:30:05.490' AS DateTime), 1)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'KEC56475  ', N'FAV63625  ', N'Mr', N'DHJ5R73GS34REHFHIASIU5CD7ZMR5FSKE9YMPB8401SYIA8WM2PCJS8745YSLBBDBJD4ITR61NHSDDVWQP52PG0L1RHJDGJPPENYX3ESAJAVAFTQA11V58NUJR3RB7TL4S3YQWZMBCS654ALXQ9BEOJGRSF50NH2PORICU0E2FKSGGPEHHTLYI7F8M', CAST(N'1975-03-30 16:43:12.090' AS DateTime), CAST(N'1999-11-19 19:28:38.110' AS DateTime), 1)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'WOB23008  ', N'LAV35233  ', N'Dr', N'YGUMOVPGGDS92QV8Z4QZB31NITV37HC9PN2X8SWXQZ81O7QV397BIMVUE63UZL9Y0IKENX067T9BU0ZA200YA0R2R7I1O1X5QA05NB75803ODK0E7TRZLBO0RHNGEZI5W3LK0TNQTVP9W2P774UVAYKELZE5OQQ9I', CAST(N'1962-09-28 15:56:32.470' AS DateTime), CAST(N'1990-05-01 23:58:21.860' AS DateTime), 1)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'WOB23008  ', N'FAV63625  ', N'Mr', N'S9YFED505P', CAST(N'1975-04-26 07:46:28.860' AS DateTime), CAST(N'1982-02-13 10:06:40.870' AS DateTime), 0)
INSERT [dbo].[Mail] ([AccountSendId], [AccountReceiveId], [Title], [Content], [SendDate], [ReadDate], [Status]) VALUES (N'FAV63625  ', N'LAV35233  ', N'Dr.', N'F4OSF9A4MVYJ7Y0FH124FQYAF9RPGP9SUS6VPD9HF5V2UQRGEYOY2L6O6EC5HLOWM5PES5P4SKOAU', CAST(N'1957-10-21 07:10:10.390' AS DateTime), CAST(N'1978-01-31 09:38:55.370' AS DateTime), 0)

INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Admin')
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Staff')
INSERT [dbo].[Roles] ([RoleName]) VALUES (N'Student')


INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'BES0630   ', N'Erich167', N'1234567', N'chienhoangduc@yahoo.com', N'Chrystal Blevins', 1, 0)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'CEF81338  ', N'Hollie', N'1234567', N'nguyenhien.viboss@gmail.com  ', N'Don Hunter', 1, 1)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'FAV63625  ', N'William', N'1234567', N'toilahung84@yahoo.com', N'Kellie Hood', 2, 1)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'HIH73849  ', N'Elton0', N'1234567', N'vantotvo.yp@gmail.com
', N'Marcy Thornton', 2, 1)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'KEC56475  ', N'Joey0', N'1234567', N'namk47@gmail.com
', N'Salvador Singh', 2, 1)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'LAV35233  ', N'Timmy26', N'1234567', N'dainam_27@gyahoo.com.vn
', N'Tracy Cuevas', 3, 1)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'SAX36635  ', N'Gail', N'1234567', N'quanghungtc07x1@gmail.com
', N'Kristine Holloway', 3, 1)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'SEN30452  ', N'Eugene', N'1234567', N'hoangdangphatco@gmail.com
', N'Earl Meza', 3, 0)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'WOB23008  ', N'Randal995', N'1234567', N'doanxd.fr@gmail.com
', N'Miriam Blackwell', 3, 1)
INSERT [dbo].[Users] ([AccountId], [Username], [Password], [Email], [DisplayName], [RoleId], [Status]) VALUES (N'ZAR422    ', N'Benjamin6', N'1234567', N'nguyenquocaic13@gmail.com', N'Kelli Cain', 3, 0)


INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'BES0630   ', N'Nguyen Van A', N'51 North Old Drive', 1, N'466-595-1273', N'BASGP4K37JY6TVUL0Z0I1FTLGYUSKKBAR1ZAMKUXHZ', N'BPUM5MTP1GDXG0DWYXN')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'CEF81338  ', N'Le Thi Q', N'75 East Hague Street', 0, N'141-506-1031', N'QW2E3UCQK3KZV13OZTK87ABFOZMDMB1IXSQ0', N'3KY5LGEATJ')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'FAV63625  ', N'Dau Ngoc P', N'955 Milton Blvd.', 0, N'4222781136', N'TMPKX0NQ9Q8JU4N', N'3OJ')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'HIH73849  ', N'Phan Quoc Z', N'35 White Fabien Parkway', 0, N'852249-4294', N'5GX', N'XH5DJRV7HS0YWXJHUOTAXSNXA')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'KEC56475  ', N'Cao Minh V', N'646 First Parkway', 1, N'074-817-6620', N'W1FCM06GX0KKSKL7TK4QXIE4B32', N'CX2B')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'LAV35233  ', N'Tran Le W', N'54 White Nobel St.', 0, N'1305590936', N'G', N'67YC3D')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'SAX36635  ', N'Ngo Minh M', N'35 West Green Hague Drive', 1, N'944-887-8147', N'SH7KEI1AJDXLNISCX56E11K3K46RH2NS0KA9DRBAKF0K222RYVMOY8ZQX3Z', N'N2QSAHAMQR4L0MWDVAHH9HUSG638C')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'SEN30452  ', N'Tang Thi O', N'71 Rocky Fabien Parkway', 0, N'604-2926796', N'212WEOZ3RE6UE79R3TFJ3NOGW78127UE9SZF7Z2I1OJDCTTH50SQ', N'ADGEQDH9J6804B0UEK75KICIISUZF')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'WOB23008  ', N'Truong Nguyen I', N'106 Cowley Street', 1, N'151-0886410', N'FWGV3DDPX2H9VG70RASJN7OESVJ', N'0S71')
INSERT [dbo].[Profiles] ([AccountId], [FullName], [Address], [Gender], [Phone], [Picture], [Class]) VALUES (N'ZAR422    ', N'Le Van Ba', N'73 East Nobel Road', 1, N'9053884203', N'7SR3OREYBS9YJ06UU98LIY8WZ1QMH38UGIQMHXNL3QLB', N'HHYKJ7')




