# AI Translator

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)


基于大模型开发的中英翻译软件，提供了前端、后端、桌面端以及 Telegram 机器人多端支持。该项目旨在帮助用户快速进行中英双向翻译，体验更自然、流畅的翻译结果。

## 地址
- 前端: [ai-translator-frontend](https://github.com/litongjava/ai-translator-frontend)  
- 后端: [ai-translator-backend](https://github.com/litongjava/ai-translator-backend)  
- 桌面端: [tauri-translator](https://github.com/litongjava/tauri-translator)  
- 在线体验: [https://translate.mycounsellor.ai/](https://translate.mycounsellor.ai/)  
- Telegram Bot: [@litongjava_bot](https://t.me/litongjava_bot)

## 功能特性

- **大模型驱动**：依托大模型提供更准确、更地道的翻译结果  
- **多端支持**  
  - **Web 前端**：在线体验，随时随地打开浏览器即可使用  
  - **后端服务**：提供 API 接口，可与其他应用或服务集成  
  - **桌面端**：基于 [Tauri](https://tauri.app/) 构建的跨平台桌面应用  
  - **Telegram 机器人**：在 Telegram 上直接调用翻译服务  
- **易于扩展**：采用模块化架构，方便后续集成更多语言或更多大模型能力  
- **开源共享**：项目完全开源，欢迎社区参与共建

## 项目结构

本项目由多个子仓库构成，功能相互独立又可协同工作：

1. **前端**  
   - 仓库地址：[ai-translator-frontend](https://github.com/litongjava/ai-translator-frontend)  
   - 提供 Web 界面和基础交互功能，调用后端接口完成翻译  

2. **后端**  
   - 仓库地址：[ai-translator-backend](https://github.com/litongjava/ai-translator-backend)  
   - 提供核心翻译逻辑和 API 接口，可与前端、桌面端以及第三方服务集成  
   
3. **桌面端**  
   - 仓库地址：[tauri-translator](https://github.com/litongjava/tauri-translator)  
   - 基于 Tauri 技术栈，支持跨平台（Windows、macOS、Linux），提供桌面级体验  

4. **线上地址**  
   - [https://translate.mycounsellor.ai/](https://translate.mycounsellor.ai/)  
   - 无需安装，打开浏览器即可快速访问和使用  

5. **Telegram 机器人端**  
   - [@litongjava_bot](https://t.me/litongjava_bot)  
   - 关注并发送消息，即可获得实时翻译结果

## 快速开始

下面以最常用的 Web 前后端模式为例，介绍如何本地部署。

### 前端部署

1. 克隆前端仓库  
   ```bash
   git clone https://github.com/litongjava/ai-translator-frontend.git
   ```
2. 安装依赖  
   ```bash
   cd ai-translator-frontend
   npm install
   ```
3. 启动开发服务器  
   ```bash
   npm run dev
   ```
4. 打包构建（可选）  
   ```bash
   npm run build
   ```
   构建完成后会在 `dist/` 目录下生成静态资源，可用于生产环境部署。

### 后端部署

1. 克隆后端仓库  
   ```bash
   git clone https://github.com/litongjava/ai-translator-backend.git
   ```
2. 安装依赖并启动服务  
   - 如果是 Java/Spring Boot 项目，请参考 `README` 或对应的文档进行构建和运行；一般可执行以下步骤：
     ```bash
     cd ai-translator-backend
     # 如果使用 Maven
     mvn clean package
     java -jar target/xxx.jar
     ```
   - 如果是 Node.js 项目，则：
     ```bash
     npm install
     npm run start
     ```
3. 配置环境变量  
   - 如果需要调用外部大模型 API（如 OpenAI），请在 `.env` 文件或系统环境变量中设置相关密钥，例如：
     ```
     OPENAI_API_KEY=your_openai_api_key
     ```
   - 其他自定义配置也可以在 `.env` 文件中进行。

4. 确认后端启动成功后，即可通过浏览器访问前端页面并进行翻译测试。

## 桌面端使用

点击[这里](https://github.com/litongjava/tauri-translator/releases/)下载客户端 安装使用
## Telegram 机器人

1. 打开 [@litongjava_bot](https://t.me/litongjava_bot)  
2. 点击 `Start`（或发送 `/start` 命令）  
3. 直接发送要翻译的文本，即可获得中英文互译结果

## 贡献指南

非常欢迎社区开发者一起参与本项目的建设：

1. **Fork 本仓库**  
2. **创建功能分支**：`git checkout -b feature/your-feature`  
3. **提交改动**：`git commit -m 'Add some feature'`  
4. **推送分支**：`git push origin feature/your-feature`  
5. **发起 Pull Request**，并等待代码审核与合并

如有任何想法或建议，欢迎在 [Issues](../../issues) 区提出讨论。

## 许可证

本项目基于 [MIT License](LICENSE) 开源，详情请查阅 [LICENSE](LICENSE) 文件。

---

感谢你对 **AI Translator** 项目的关注和使用，希望这个项目能为你的翻译需求带来帮助。如果你觉得这个项目对你有所帮助，欢迎 **Star** 支持并分享给更多人！