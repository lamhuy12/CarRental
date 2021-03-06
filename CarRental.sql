USE [master]
GO
/****** Object:  Database [RentalCar]    Script Date: 3/19/2021 10:48:13 PM ******/
CREATE DATABASE [RentalCar]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'RentalCar', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\RentalCar.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'RentalCar_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\RentalCar_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [RentalCar] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [RentalCar].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [RentalCar] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [RentalCar] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [RentalCar] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [RentalCar] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [RentalCar] SET ARITHABORT OFF 
GO
ALTER DATABASE [RentalCar] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [RentalCar] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [RentalCar] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [RentalCar] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [RentalCar] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [RentalCar] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [RentalCar] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [RentalCar] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [RentalCar] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [RentalCar] SET  DISABLE_BROKER 
GO
ALTER DATABASE [RentalCar] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [RentalCar] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [RentalCar] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [RentalCar] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [RentalCar] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [RentalCar] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [RentalCar] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [RentalCar] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [RentalCar] SET  MULTI_USER 
GO
ALTER DATABASE [RentalCar] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [RentalCar] SET DB_CHAINING OFF 
GO
ALTER DATABASE [RentalCar] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [RentalCar] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [RentalCar] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [RentalCar] SET QUERY_STORE = OFF
GO
USE [RentalCar]
GO
/****** Object:  Table [dbo].[CarDetail]    Script Date: 3/19/2021 10:48:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CarDetail](
	[CarID] [nvarchar](50) NOT NULL,
	[CarName] [nvarchar](50) NOT NULL,
	[Color] [nvarchar](50) NOT NULL,
	[Year] [int] NOT NULL,
	[CateID] [nvarchar](50) NOT NULL,
	[Price] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
 CONSTRAINT [PK_CarDetail] PRIMARY KEY CLUSTERED 
(
	[CarID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 3/19/2021 10:48:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CateID] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 3/19/2021 10:48:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[DiscountID] [nvarchar](50) NOT NULL,
	[Value] [int] NOT NULL,
	[ContentDiscount] [nvarchar](50) NOT NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NOT NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[DiscountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 3/19/2021 10:48:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[FeedbackID] [nvarchar](50) NOT NULL,
	[ContentFeedback] [nvarchar](500) NOT NULL,
	[Rate] [int] NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Feedback] PRIMARY KEY CLUSTERED 
(
	[FeedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 3/19/2021 10:48:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderDetailID] [nvarchar](50) NOT NULL,
	[OrderID] [nvarchar](50) NOT NULL,
	[CarID] [nvarchar](50) NOT NULL,
	[Amount] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[FeedbackID] [nvarchar](50) NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC,
	[OrderID] ASC,
	[CarID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 3/19/2021 10:48:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Total] [float] NOT NULL,
	[RentalDate] [date] NOT NULL,
	[ReturnDate] [date] NOT NULL,
	[Status] [bit] NOT NULL,
	[DateCreate] [datetime] NOT NULL,
	[DiscountID] [nvarchar](50) NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 3/19/2021 10:48:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[Email] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Phone] [nvarchar](20) NOT NULL,
	[Address] [nvarchar](500) NOT NULL,
	[Role] [nvarchar](10) NOT NULL,
	[Status] [bit] NOT NULL,
	[CreateDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Registration_1] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S01', N'a', N'blue', 2001, N'C01', 5000, 4)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S02', N'b', N'red', 2004, N'C01', 1234, 2)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S03', N'c', N'yellow', 2019, N'C02', 524, 1)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S04', N'abc', N'purple', 2001, N'C02', 999, 6)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S05', N'Sirius', N'red', 2000, N'C03', 99, 8)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S06', N'LUXA', N'blue', 2020, N'C08', 2000, 2)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S07', N'LUXSA', N'black', 2019, N'C08', 2500, 5)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S08', N'Fadil', N'white', 2021, N'C08', 1500, 3)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S09', N'Aventador', N'green', 2020, N'C07', 3231, 2)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S10', N'Sian ', N'yellow', 2020, N'C07', 4231, 1)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S11', N'A5', N'black', 2000, N'C05', 1231, 2)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S12', N'Q7', N'white', 1999, N'C05', 500, 1)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S13', N'Series 5', N'red', 2020, N'C04', 1500, 3)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S14', N'Series 4', N'blue', 2017, N'C04', 900, 2)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S15', N'Series 7', N'silver', 2005, N'C04', 1600, 3)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S16', N'ES', N'white', 2021, N'C06', 1700, 2)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S17', N'GS', N'black', 2020, N'C06', 1800, 1)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S18', N'NX', N'black', 2019, N'C06', 1200, 5)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S19', N'Phantom', N'black', 2020, N'C09', 3500, 5)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S20', N'Ghost', N'black', 2019, N'C09', 3333, 2)
INSERT [dbo].[CarDetail] ([CarID], [CarName], [Color], [Year], [CateID], [Price], [Quantity]) VALUES (N'S21', N'Luanches', N'white', 2020, N'C09', 4444, 8)
GO
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C01', N'HONDA')
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C02', N'YAMAHA')
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C03', N'MERCEDES')
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C04', N'BMW')
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C05', N'Audi')
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C06', N'Lexus')
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C07', N'Lamborghini')
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C08', N'Vinfast')
INSERT [dbo].[Category] ([CateID], [Name]) VALUES (N'C09', N'Roll Roys')
GO
INSERT [dbo].[Discount] ([DiscountID], [Value], [ContentDiscount], [StartDate], [EndDate]) VALUES (N'001', 20, N'mung 8/3', CAST(N'2021-03-18' AS Date), CAST(N'2021-05-23' AS Date))
INSERT [dbo].[Discount] ([DiscountID], [Value], [ContentDiscount], [StartDate], [EndDate]) VALUES (N'002', 15, N'mung 8/3', CAST(N'2021-03-15' AS Date), CAST(N'2021-03-18' AS Date))
INSERT [dbo].[Discount] ([DiscountID], [Value], [ContentDiscount], [StartDate], [EndDate]) VALUES (N'003', 10, N'haha', CAST(N'2021-06-01' AS Date), CAST(N'2021-06-02' AS Date))
GO
INSERT [dbo].[Feedback] ([FeedbackID], [ContentFeedback], [Rate], [CreateDate], [Email]) VALUES (N'FB01', N'aaaa', 6, CAST(N'2021-03-17T15:24:46.953' AS DateTime), N'huyv46@gmail.com')
INSERT [dbo].[Feedback] ([FeedbackID], [ContentFeedback], [Rate], [CreateDate], [Email]) VALUES (N'FB2', N'abac', 6, CAST(N'2021-03-17T15:25:47.980' AS DateTime), N'huyv46@gmail.com')
INSERT [dbo].[Feedback] ([FeedbackID], [ContentFeedback], [Rate], [CreateDate], [Email]) VALUES (N'FB3', N'aaaa', 8, CAST(N'2021-03-17T15:27:27.377' AS DateTime), N'huyv46@gmail.com')
INSERT [dbo].[Feedback] ([FeedbackID], [ContentFeedback], [Rate], [CreateDate], [Email]) VALUES (N'FB4', N'aaaa', 8, CAST(N'2021-03-17T15:27:41.820' AS DateTime), N'huyv46@gmail.com')
INSERT [dbo].[Feedback] ([FeedbackID], [ContentFeedback], [Rate], [CreateDate], [Email]) VALUES (N'FB5', N'aaaa', 8, CAST(N'2021-03-17T15:28:02.173' AS DateTime), N'huyv46@gmail.com')
INSERT [dbo].[Feedback] ([FeedbackID], [ContentFeedback], [Rate], [CreateDate], [Email]) VALUES (N'FB6', N'aaaa', 8, CAST(N'2021-03-17T15:28:06.837' AS DateTime), N'huyv46@gmail.com')
INSERT [dbo].[Feedback] ([FeedbackID], [ContentFeedback], [Rate], [CreateDate], [Email]) VALUES (N'FB7', N'abababa', 7, CAST(N'2021-03-19T21:40:50.940' AS DateTime), N'huyv46@gmail.com')
GO
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-13-0', N'huyv46@gmail.com-13', N'S21', 4, 17776, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-14-0', N'huyv46@gmail.com-14', N'S21', 4, 13332, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-15-0', N'huyv46@gmail.com-15', N'S08', 3, 4500, N'FB7')
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-17-0', N'huyv46@gmail.com-17', N'S04', 3, 2997, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-22-0', N'huyv46@gmail.com-22', N'S04', 1, 19980, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-23-0', N'huyv46@gmail.com-23', N'S21', 1, 88880, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-24-0', N'huyv46@gmail.com-24', N'S06', 1, 400, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-25-0', N'huyv46@gmail.com-25', N'S01', 1, 5000, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-26-0', N'huyv46@gmail.com-26', N'S06', 1, 2000, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-27-0', N'huyv46@gmail.com-27', N'S07', 2, 5000, NULL)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [CarID], [Amount], [Price], [FeedbackID]) VALUES (N'huyv46@gmail.com-9-0', N'huyv46@gmail.com-9', N'S05', 1, 99, NULL)
GO
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-13', N'huyv46@gmail.com', 17776, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-18T21:35:44.373' AS DateTime), NULL)
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-14', N'huyv46@gmail.com', 13332, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 0, CAST(N'2021-03-18T23:32:18.067' AS DateTime), NULL)
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-15', N'huyv46@gmail.com', 4500, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T00:02:57.680' AS DateTime), NULL)
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-16', N'huyv46@gmail.com', 4444, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T00:45:30.393' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-17', N'huyv46@gmail.com', 2997, CAST(N'2021-03-22' AS Date), CAST(N'2021-03-23' AS Date), 1, CAST(N'2021-03-19T20:00:27.020' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-18', N'huyv46@gmail.com', 2500, CAST(N'2021-03-23' AS Date), CAST(N'2021-03-24' AS Date), 1, CAST(N'2021-03-19T20:05:23.460' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-19', N'huyv46@gmail.com', 999, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T20:11:14.097' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-20', N'huyv46@gmail.com', 1998, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T20:13:42.827' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-21', N'huyv46@gmail.com', 999, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T20:15:10.853' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-22', N'huyv46@gmail.com', 999, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T20:17:32.093' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-23', N'huyv46@gmail.com', 4444, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T21:46:45.343' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-24', N'huyv46@gmail.com', 2000, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T21:49:55.283' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-25', N'huyv46@gmail.com', -95000, CAST(N'2021-03-26' AS Date), CAST(N'2021-03-27' AS Date), 1, CAST(N'2021-03-19T21:54:06.420' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-26', N'huyv46@gmail.com', 1600, CAST(N'2021-03-25' AS Date), CAST(N'2021-03-26' AS Date), 1, CAST(N'2021-03-19T21:54:54.117' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-27', N'huyv46@gmail.com', 4000, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date), 1, CAST(N'2021-03-19T22:15:37.437' AS DateTime), N'001')
INSERT [dbo].[Orders] ([OrderID], [Email], [Total], [RentalDate], [ReturnDate], [Status], [DateCreate], [DiscountID]) VALUES (N'huyv46@gmail.com-9', N'huyv46@gmail.com', 99, CAST(N'2021-03-25' AS Date), CAST(N'2021-03-26' AS Date), 1, CAST(N'2021-03-17T21:41:44.837' AS DateTime), NULL)
GO
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'a', N'123', N'haha', N'123', N'123', N'user', 0, CAST(N'1970-01-01T07:00:00.000' AS DateTime))
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'aaaa', N'123', N'aaa', N'125', N'544', N'user', 0, CAST(N'2021-03-03T11:28:55.703' AS DateTime))
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'abc', N'abc', N'123', N'123', N'123', N'user', 0, CAST(N'2021-03-03T13:27:19.823' AS DateTime))
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'admin@gmail.com', N'123', N'haha', N'0123', N'abcd', N'admin', 1, CAST(N'1900-01-01T00:00:06.687' AS DateTime))
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'huyv46@gmail.com', N'123', N'Vũ Lâm Huy', N'0166 674 2484', N'Tx. Đồng Xoài', N'user', 1, CAST(N'2021-03-03T13:46:47.253' AS DateTime))
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'huyvlse140164@fpt.edu.vn', N'123', N'213', N'213', N'123', N'user', 1, CAST(N'2021-03-19T10:48:01.177' AS DateTime))
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [Role], [Status], [CreateDate]) VALUES (N'tuankhung3001@gmail.com', N'123', N'taung', N'123', N'123', N'user', 1, CAST(N'2021-03-15T05:21:00.000' AS DateTime))
GO
ALTER TABLE [dbo].[CarDetail]  WITH CHECK ADD  CONSTRAINT [FK_CarDetail_Category] FOREIGN KEY([CateID])
REFERENCES [dbo].[Category] ([CateID])
GO
ALTER TABLE [dbo].[CarDetail] CHECK CONSTRAINT [FK_CarDetail_Category]
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD  CONSTRAINT [FK_Feedback_Registration] FOREIGN KEY([Email])
REFERENCES [dbo].[Registration] ([Email])
GO
ALTER TABLE [dbo].[Feedback] CHECK CONSTRAINT [FK_Feedback_Registration]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_CarDetail] FOREIGN KEY([CarID])
REFERENCES [dbo].[CarDetail] ([CarID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_CarDetail]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Feedback] FOREIGN KEY([FeedbackID])
REFERENCES [dbo].[Feedback] ([FeedbackID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Feedback]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Orders]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Discount] FOREIGN KEY([DiscountID])
REFERENCES [dbo].[Discount] ([DiscountID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Discount]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Registration] FOREIGN KEY([Email])
REFERENCES [dbo].[Registration] ([Email])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Registration]
GO
USE [master]
GO
ALTER DATABASE [RentalCar] SET  READ_WRITE 
GO
