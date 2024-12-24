const CACHE_NAME = 'ischigualasto-v2';
const urlsToCache = [
  './',
  './index.html',
  './manifest.json',
  './styles.css',
  './original_icon.png'
];

// Instalación del service worker
self.addEventListener('install', event => {
  event.waitUntil(
    caches.delete(CACHE_NAME).then(() => {
      return caches.open(CACHE_NAME);
    }).then(cache => {
      return cache.addAll(urlsToCache);
    })
  );
});

// Interceptar peticiones
self.addEventListener('fetch', event => {
  event.respondWith(
    fetch(event.request)
      .catch(() => {
        return caches.match(event.request);
      })
  );
});

// Activación y limpieza de caches antiguas
self.addEventListener('activate', event => {
  event.waitUntil(
    caches.keys().then(cacheNames => {
      return Promise.all(
        cacheNames.filter(cacheName => {
          return cacheName !== CACHE_NAME;
        }).map(cacheName => {
          return caches.delete(cacheName);
        })
      );
    })
  );
});
