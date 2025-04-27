FROM node:20-alpine AS builder

WORKDIR /weatherapp
COPY package*.json angular.json tsconfig.* ./
COPY src/ ./src/
RUN npm install --legacy-peer-deps

ARG configuration=production
RUN npm run build --configuration=$configuration

FROM nginx:stable-alpine
COPY --from=builder /weatherapp/dist/weatherapp-fe/browser /usr/share/nginx/html
COPY nginx-conf/default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
