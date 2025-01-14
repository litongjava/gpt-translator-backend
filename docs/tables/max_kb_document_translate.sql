/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.9-postgres
 Source Server Type    : PostgreSQL
 Source Server Version : 160003
 Source Host           : 192.168.3.9:5432
 Source Catalog        : gpt_translator
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160003
 File Encoding         : 65001

 Date: 02/12/2024 19:51:10
*/


-- ----------------------------
-- Table structure for max_kb_document_translate
-- ----------------------------
DROP TABLE IF EXISTS "public"."max_kb_document_translate";
CREATE TABLE "public"."max_kb_document_translate" (
  "id" "pg_catalog"."int8" NOT NULL,
  "name" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "src_lang" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "dst_lang" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" NOT NULL,
  "src_content" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "dst_content" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "files" "pg_catalog"."jsonb",
  "remark" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "creator" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "create_time" "pg_catalog"."timestamptz" NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" "pg_catalog"."varchar" COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "update_time" "pg_catalog"."timestamptz" NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "deleted" "pg_catalog"."int2" DEFAULT 0,
  "tenant_id" "pg_catalog"."int8" NOT NULL DEFAULT 0
)
;

-- ----------------------------
-- Records of max_kb_document_translate
-- ----------------------------
INSERT INTO "public"."max_kb_document_translate" VALUES (453512876297093120, 'ICS141-Session1--Intro.pdf', 'English', 'Chinese', '
# Welcome to ICS 141

## ICS 141 – Intro Lecture
**Instructor:** Lisa Miller, MS  
**Kapiolani Community College**

_Page 1/19_

# Who am I?

- Lisa Miller
  - MS in Computer Science, UH Mānoa
  - PhD student in CS, machine learning at Mānoa

![Image](images/image1.png)

1. Each new state matched to most similar old state; transitions transferred to new states.
2. ![Image](images/image2.png)
3. ![Image](images/image3.png)

2/19

# First Extra Credit!

1. **Add a Laulima profile picture**
   - Left side menu click “Profile”
   - Add a picture of yourself to your profile.

2. **Make a Discord account** and join the KapCC ICS 141 server (link on Laulima)

3. **Fill out ICS Class Introduction form** (link on Laulima)
   - 10 points if you complete by Friday.

---

_Page 3/19_

# Back to: What is ICS 141?

- Discrete Mathematics for Computer Science I!

## What is Discrete Mathematics?

---

Page: 4/19

# What is Discrete Math?

"Discrete Math" is not the name of a branch of mathematics, like number theory, algebra, calculus, etc.

Rather, it''s a description of a set of branches of math that all have in common the feature that they are "discrete" rather than "continuous".

[http://www.cse.buffalo.edu/~rapaport/191/S09/whatisdiscmath.html](http://www.cse.buffalo.edu/~rapaport/191/S09/whatisdiscmath.html)

5/19

# What is Discrete Math?

## What is “discrete” rather than “continuous”?

- The real numbers are continuous
  - Between two numbers, there are infinitely more.
    - 2.0, 2.00001, 2.0001, 2.0000100000001, ..., 2.1
  - When you plot continuous functions, you get a solid line
- Nature works this way (above quantum scale)
- “analog”

![Riemann integral with a regular partition, representing the definite integral: wikimedia commons](images/riemann_integral.png)

6/19

# What is Discrete Math?

- Computers are digital machines.
  - Everything represented sequences of 0s and 1s.
  - Cannot do infinite.
  - All computer math, logic, operations, etc. MUST come down to a difference between a 0 and a 1.

![Riemann integral with a regular partition, representing the definite integral: wikimedia commons](images/riemann_integral.png)

# What is Discrete Math?

- Computers are digital machines.
- All computers can do is “discrete”
  - individually separate and distinct.
  - synonyms: separate, distinct, individual, detached, unattached, disconnected, discontinuous, disjunct, disjoined

![Riemann integral with a regular partition, representing the definite integral](images/riemann_integral.png)

8 / 19
`
# What is Discrete Math?

- Discrete Math (aka Discrete Structures) covers most mathematics used in computer science.
  - Kind of a hodge-podge of topics

![Riemann integral with a regular partition, representing the definite integral](images/riemann_integral.png)

9/19
# Abstraction Layers

## Overview

### Layers of Abstraction
- **Application**
- **Algorithm**
- **Programming Language**
- **Assembly Language**
- **Machine Code**
- **Instruction Set Architecture**
- **Micro Architecture**
- **Gates/Registers**
- **Devices (Transistors)**
- **Physics**

## Increasing Order of Complexity
- **Increasing order of Abstraction** (from top to bottom)

## Software
- Software engineers
- Software designers
- Algorithm developers
- Programmers

## Programming Language
- Programming language compilers/interpreters

## Fields
- **Computer Science**
- **Computer and Electrical Engineering**  

## Page Number
10/19
# Algorithms

- A well-defined sequence of steps, explained clearly enough that even a computer could do them. (perldoc.perl.org)

- A set of instructions, typically to solve a class of problems or perform a computation. Algorithms are unambiguous specifications for performing calculation, data processing, automated reasoning, and other tasks. (wikipedia)

- An algorithm is like a recipe for a computer to follow.

![Image](images/algorithm_diagram.png)

---

Page: 11/19

# What is ICS 141 then?

- Awesome syllabus is on the course Laulima Homepage

---

Page: 12 / 19

# KCC Computer Science Transfer Degree

## ASNS-ICT Degree STEM Courses
Transferable to UH Manoa Computer Science BS Degree

### Required ICS Courses
- **ICS 111**: Intro to CS 1 (Java)
- **ICS 211**: Intro to CS 2 (Java)
- **ICS 212**: Program Structure (C/C++)
- **ICS 141**: Discrete Math 1
- **ICS 241**: Discrete Math 2

### Required Other STEM
- **MATH 103**
- **MATH 135**
- **MATH 140**

### Recommended Electives
- **MATH 241**
- **MATH 242**
- **PHYS 151** + Lab
- **PHYS 152** + Lab
- **CHEM 161** + Lab
- **CHEM 162** + Lab

### Non-transferable Prerequisites
`# ASNS ICT Prerequisites

## Course Prerequisites Overview

- **ICS 141**
  - Qualifies for **MATH 135**
    - Leads to **ICS 241**
      - Connects to **ICS 111**
      - Connects to **ICS 211**
      - Connects to **ICS 212**

### Diagram Representation
```plaintext
       +-----+
       | ICS 111 |
       +-----+
        /       \
       /         \
+-----+           +-----+
| ICS 241   |------| ICS 211 |
+-----+    |       +-----+
   |       |
   |       |
+-----+    |
| ICS 141   |
+-----+     |
             |
             +-----+
                   | ICS 212 |
                   +-----+
```
# ASNS ICS Course Timing

- **Qual. for MATH 135**

## Course Flowchart

- **ICS 141**
  - Prerequisites: 
    - Leads to **ICS 241** (Fall Only)

- **ICS 111**
  - Floats to **ICS 211**
  - Floats to **ICS 212** (Spring Only)

## Course Offerings

- **Fall Only Courses:**
  - ICS 241

- **Spring Only Courses:**
  - ICS 212# ASNS ICT to Mānoa BA/BS

## Course Flow

- **ICS 141**
- **ICS 241**
- **ICS 111**  
  *All ASNS classes required for BS or BA*
- **ICS 211**
- **ICS 212**

### Additional Courses

- **ICS 311 (at UHM)**
- **ICS 312, 313, 321, ...**
- **ICS 312, 313, 331, ...**
# UH Mānoa BS in CE

![ICS 141](images/ICS_141.png)

**UHM Computer Engineering requires ICS 141**

---

_Page 17/19_

# Want More Information?

- **CS Degree Info** → Laulima Resources folder
- Talk to me
- Talk to Lisa Yrizarry in Maida Kamber Counseling Center
  - Email: [wongla@hawaii.edu](mailto:wongla@hawaii.edu)
- Student developed STEM website: [https://kccstem.com/](https://kccstem.com/)

Page 18/19

# Homework for Thursday

1) Look at the textbook!
   - There will be homework assigned Thursday
   - It will be due the following Thursday.

2) Upload your Laulima Profile picture, make Discord account, fill out introduction form.

---
**Page 19/19**
', '
# 欢迎来到 ICS 141

## ICS 141 – 入门讲座
**讲师：** Lisa Miller, MS  
**卡皮奥拉尼社区学院**

_第1页/共19页_

# 我是谁？

- Lisa Miller
  - 夏威夷大学马诺阿分校计算机科学硕士
  - 马诺阿分校计算机科学博士生，机器学习方向

![图片](images/image1.png)

1. 每个新状态匹配到最相似的旧状态；过渡转移到新状态。
2. ![图片](images/image2.png)
3. ![图片](images/image3.png)

2/19

# 首次额外积分！

1. **添加Laulima个人资料图片**
   - 左侧菜单点击“个人资料”
   - 将您的照片添加到您的个人资料。

2. **创建Discord账号**并加入KapCC ICS 141服务器（链接在Laulima上）

3. **填写ICS课程简介表**（链接在Laulima上）
   - 若在周五前完成，可得10分。

---

_第3页/共19页_

# 回到：什么是ICS 141？

- 计算机科学离散数学I！

## 什么是离散数学？

---

第4页/共19页

# 什么是离散数学？

“离散数学”并不是像数论、代数、微积分等的一个数学分支的名称。

而是对一组数学分支的描述，这些分支共同的特点是它们是“离散的”而非“连续的”。

[http://www.cse.buffalo.edu/~rapaport/191/S09/whatisdiscmath.html](http://www.cse.buffalo.edu/~rapaport/191/S09/whatisdiscmath.html)

5/19

# 什么是离散数学？

## 什么是“离散的”而不是“连续的”？

- 实数是连续的
  - 在两个数之间，还有无限多个数。
    - 2.0, 2.00001, 2.0001, 2.0000100000001, ..., 2.1
  - 当您绘制连续函数图时，您得到一条实线
- 自然界以这种方式运作（量子尺度以上）
- “模拟”

![用于表示定积分的黎曼积分：维基媒体公用](images/riemann_integral.png)

6/19

# 什么是离散数学？

- 计算机是数字机器。
  - 一切都以0和1的序列表示。
  - 无法做到无限。
  - 所有计算机数学、逻辑、操作等必须归结为0和1之间的差异。

![用于表示定积分的黎曼积分：维基媒体公用](images/riemann_integral.png)

# 什么是离散数学？

- 计算机是数字机器。
- 所有计算机能做的都是“离散的”
  - 单独分开且独立。
  - 同义词：分离的，独特的，个别的，分开的，未连接的，不连续的，分段的，离散的

![用于表示定积分的黎曼积分](images/riemann_integral.png)

8/19

# 什么是离散数学？

- 离散数学（又称离散结构）涵盖了计算机科学中使用的大多数数学。
  - 各种各样的主题

![用于表示定积分的黎曼积分](images/riemann_integral.png)

9/19

# 抽象层次

## 概述

### 抽象层次
- **应用程序**
- **算法**
- **编程语言**
- **汇编语言**
- **机器码**
- **指令集架构**
- **微架构**
- **门/寄存器**
- **设备（晶体管）**
- **物理学**

## 复杂性递增顺序
- **抽象递增顺序**（从上到下）

## 软件
- 软件工程师
- 软件设计师
- 算法开发者
- 程序员

## 编程语言
- 编程语言编译器/解释器

## 领域
- **计算机科学**
- **计算机与电气工程**

## 页码
10/19

# 算法

- 一系列明确定义的步骤，讲解得足够清楚以便计算机可以执行。(perldoc.perl.org)

- 一组指令，通常用于解决某类问题或执行计算。算法是执行计算、数据处理、自动推理和其他任务的明确规范。(维基百科)

- 算法就像计算机要遵循的食谱。

![图片](images/algorithm_diagram.png)

---

第11页/共19页

# 那么ICS 141是什么呢？

- 棒极了的课程大纲在Laulima课程主页上

---

第12页/共19页

# KCC计算机科学转学学位

## ASNS-ICT学位STEM课程
可转学至UH Mānoa计算机科学学士学位

### 必修ICS课程
- **ICS 111**: CS入门1 (Java)
- **ICS 211**: CS入门2 (Java)
- **ICS 212**: 程序结构 (C/C++)
- **ICS 141**: 离散数学1
- **ICS 241**: 离散数学2

### 必修其他STEM
- **MATH 103**
- **MATH 135**
- **MATH 140**

### 推荐选修课
- **MATH 241**
- **MATH 242**
- **PHYS 151** + 实验室
- **PHYS 152** + 实验室
- **CHEM 161** + 实验室
- **CHEM 162** + 实验室

### 不可转学的先修课程
`# ASNS ICT先修课程

## 课程先修课程概述

- **ICS 141**
  - 符合**MATH 135**资格
    - 通向**ICS 241**
      - 连接到**ICS 111**
      - 连接到**ICS 211**
      - 连接到**ICS 212**

### 图表示
```plaintext
       +-----+
       | ICS 111 |
       +-----+
        /       \
       /         \
+-----+           +-----+
| ICS 241   |------| ICS 211 |
+-----+    |       +-----+
   |       |
   |       |
+-----+    |
| ICS 141   |
+-----+     |
             |
             +-----+
                   | ICS 212 |
                   +-----+
```
# ASNS ICS课程时间

- **符合MATH 135资格**

## 课程流程图

- **ICS 141**
  - 先修课程：
    - 通向**ICS 241**（仅秋季）

- **ICS 111**
  - 承接至**ICS 211**
  - 承接至**ICS 212**（仅春季）

## 课程设置

- **仅秋季课程：**
  - ICS 241

- **仅春季课程：**
  - ICS 212# ASNS ICT至Mānoa BA/BS

## 课程流程

- **ICS 141**
- **ICS 241**
- **ICS 111**  
  *所有ASNS课程均为BS或BA必修*
- **ICS 211**
- **ICS 212**

### 附加课程

- **ICS 311（在UHM）**
- **ICS 312, 313, 321, ...**
- **ICS 312, 313, 331, ...**
# UH Mānoa CE学士

![ICS 141](images/ICS_141.png)

**UHM计算机工程要求ICS 141**

---

_第17页/共19页_

# 想要更多信息？

- **CS学位信息** → Laulima资源文件夹
- 联系我
- 联系Maida Kamber咨询中心的Lisa Yrizarry
  - 邮箱：[wongla@hawaii.edu](mailto:wongla@hawaii.edu)
- 学生开发的STEM网站：[https://kccstem.com/](https://kccstem.com/)

第18页/共19页

# 周四作业

1) 查看教材！
   - 周四将分配作业
   - 作业将在下周四截止。

2) 上传您的Laulima个人资料图片，创建Discord账号，填写简介表。

---
**第19页/共19页**', '[{"id": "453512791798644736", "uid": "rc-upload-1733201664765-4", "url": "https://imagi.s3.us-west-1.amazonaws.com/translate/453512777775022080.pdf", "name": "ICS141-Session1--Intro.pdf", "size": 924466, "type": "application/pdf", "status": "done"}]', NULL, '', '2024-12-03 04:58:34.965693+00', '', '2024-12-03 04:58:34.965693+00', 0, 0);

-- ----------------------------
-- Primary Key structure for table max_kb_document_translate
-- ----------------------------
ALTER TABLE "public"."max_kb_document_translate" ADD CONSTRAINT "max_kb_document_translate_pkey" PRIMARY KEY ("id");
